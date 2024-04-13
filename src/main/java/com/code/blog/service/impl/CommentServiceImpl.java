package com.code.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.constants.CodeEnum;
import com.code.blog.constants.CommentTypeEnum;
import com.code.blog.constants.LikeEnum;
import com.code.blog.constants.RedisConstants;
import com.code.blog.entity.Article;
import com.code.blog.entity.Comment;
import com.code.blog.entity.Like;
import com.code.blog.entity.User;
import com.code.blog.entity.dto.CommentDTO;
import com.code.blog.entity.dto.LikeDTO;
import com.code.blog.entity.vo.CommentVO;
import com.code.blog.exception.CustomException;
import com.code.blog.service.ArticleService;
import com.code.blog.service.CommentService;
import com.code.blog.mapper.CommentMapper;
import com.code.blog.service.LikeService;
import com.code.blog.service.UserService;
import com.code.blog.utils.RandomUtils;
import com.code.blog.utils.RedisUtil;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author HeXin
* @description 针对表【comment(文章评论表)】的数据库操作Service实现
* @createDate 2024-03-09 10:39:33
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RandomUtils randomUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    /**
     * 保存评论
     *
     * @param commentDTO 评论dto
     * @return {@link Boolean}
     */
    @Override
    public Boolean save(CommentDTO commentDTO) {
        String content = Jsoup.clean(commentDTO.getCommentContent(), Safelist.basic());
        if(!StringUtils.hasText(content)) {
            throw new CustomException(CodeEnum.COMMENT_NOT_COMPLIANCE);
        }
        // 评论脱敏操作
        SensitiveWordHelper.replace(content);
        commentDTO.setCommentContent(content);
        if(CommentTypeEnum.getEnumByCode(commentDTO.getType()) == null) {
            throw new CustomException(CodeEnum.COMMENT_TYPE_ERROR);
        }

        if(CommentTypeEnum.COMMENT_TYPE_ARTICLE.getType().equals(commentDTO.getType())) {
            Article one = articleService.lambdaQuery().eq(Article::getId, commentDTO.getSource())
                    .select(Article::getUserId, Article::getArticleTitle, Article::getCommentStatus).one();
            if(one == null) {
                throw new CustomException(CodeEnum.ARTICLE_NOT_FOUND);
            }
            if(one.getCommentStatus().equals(0)){
                throw new CustomException(CodeEnum.ARTICLE_COMMENT_CLOSED);
            }
        }
        return save(commentDTO.toPo(Comment.class));
    }

    @Override
    public Boolean delete(Long id) {
        // 检查该评论下是否有子评论
        Long count = lambdaQuery().eq(Comment::getFloorCommentId, id).count();
        if(count > 0) {
            List<Comment> comments = lambdaQuery().eq(Comment::getFloorCommentId, id).list();
            for (Comment comment : comments) {
                delete(comment.getId());
            }

        }
        return removeById(id);
    }

    @Override
    public Integer count(Long source, String type) {
        Integer count = redisUtil.get(RedisConstants.COMMENT_COUNT.getKey() + source + ":" + type, Integer.class);
        if(count != null) {
            return count;
        }
        count = Math.toIntExact(lambdaQuery().eq(Comment::getSource, source).eq(Comment::getType, type).count());
        redisUtil.set(RedisConstants.COMMENT_COUNT.getKey() + source + ":" + type, count);
        return count;
    }

    @Override
    public IPage<Comment> list(Long uid, Long resource, String type, Long pageNum, Long pageSize) {
        if(CommentTypeEnum.COMMENT_TYPE_ARTICLE.getType().equals(type)){
            Article one = articleService.lambdaQuery().eq(Article::getId, resource)
                    .select(Article::getCommentStatus).one();
            if(one == null) {
                throw new CustomException(CodeEnum.ARTICLE_NOT_FOUND);
            }
            if(one.getCommentStatus().equals(0)) {
                throw new CustomException(CodeEnum.ARTICLE_COMMENT_CLOSED);
            }
        }
        return lambdaQuery().eq(resource != null, Comment::getSource, resource)
                .eq(StringUtils.hasText(type), Comment::getType, type)
                .eq(uid != null, Comment::getUserId, uid)
                .page(new Page<>(pageNum, pageSize));
    }

    @Override
    public IPage<CommentVO> list(CommentDTO dto) {
        IPage<CommentVO> resultPage = new Page<>(1,8);
        if(CommentTypeEnum.COMMENT_TYPE_ARTICLE.getType().equals(dto.getType())){
            Article one = articleService.lambdaQuery().eq(Article::getId, dto.getSource())
                    .select(Article::getCommentStatus).one();
            if(one == null) {
                throw new CustomException(CodeEnum.ARTICLE_NOT_FOUND);
            }
            if(one.getCommentStatus().equals(0)) {
                throw new CustomException(CodeEnum.ARTICLE_COMMENT_CLOSED);
            }
        }
        IPage<Comment> page;
        if(dto.getFloorCommentId() == null) {
            page = lambdaQuery().eq(Comment::getSource, dto.getSource())
                    .eq(StringUtils.hasText(dto.getType()), Comment::getType, dto.getType())
                    .eq(Comment::getParentCommentId, 0)
                    .orderByAsc(Comment::getGmtCreate)
                    .page(new Page<>(1, 8));
            List<Comment> comments = page.getRecords();
            if(CollectionUtils.isEmpty(comments)){
                return page.convert(this::buildVO);
            }
            List<CommentVO> commentVOS = comments.stream().map(comment ->{
                CommentVO vo = buildVO(comment);
                Page commentPage = lambdaQuery().eq(Comment::getSource,dto.getSource())
                        .eq(StringUtils.hasText(dto.getType()), Comment::getType, dto.getType())
                        .eq(Comment::getFloorCommentId,comment.getId())
                        .orderByAsc(Comment::getGmtCreate).page(new Page(1,8));
                List<Comment> childComments = commentPage.getRecords();
                if(!CollectionUtils.isEmpty(childComments)){
                    List<CommentVO> ccVO = childComments.stream().map(this::buildVO).toList();
                    commentPage.setOrders(ccVO);
                }
                vo.setChildComments(commentPage.setRecords(commentPage.getRecords().stream().map(this::buildVO).toList()));
                return vo;
            }).toList();
            resultPage.setRecords(commentVOS);
        } else {
            page = lambdaQuery().eq(Comment::getSource, dto.getSource())
                    .eq(StringUtils.hasText(dto.getType()), Comment::getType, dto.getType())
                    .eq(Comment::getFloorCommentId, dto.getFloorCommentId())
                    .orderByAsc(Comment::getGmtCreate).page(new Page<>(1, 8));
            List<Comment> childComments = page.getRecords();
            List<CommentVO> ccVO = childComments.stream().map(this::buildVO).collect(Collectors.toList());
            resultPage.setRecords(ccVO);
        }
        resultPage.setTotal(page.getTotal());
        resultPage.setCurrent(page.getCurrent());
        resultPage.setPages(page.getPages());
        resultPage.setSize(page.getSize());
        return resultPage;
    }

    @Override
    public Boolean like(Long id) {
        // 获取当前登录用户的id
        Long uid = StpUtil.getLoginIdAsLong();
        Comment comment = lambdaQuery().eq(Comment::getId, id).one();
        if(comment == null) {
            throw new CustomException(CodeEnum.COMMENT_NOT_FOUND);
        }
        Like like = likeService.get(uid, LikeEnum.COMMENT.ordinal(), id);
        if(like != null){
            return cancelLike(id);
        }
        likeService.like(new LikeDTO().setUid(uid).setType(LikeEnum.COMMENT.ordinal()).setIdentifier(id));
        comment.setLikeCount(comment.getLikeCount() + 1);
        return updateById(comment);
    }
    /**
     * 取消点赞
     *
     * @param id 评论id
     * @return {@link Boolean}
     */
    private Boolean cancelLike(Long id) {
        Long uid = StpUtil.getLoginIdAsLong();
        Like like = likeService.get(uid, LikeEnum.COMMENT.ordinal(), id);
        if(like == null) {
            throw new CustomException(CodeEnum.NOT_FOUND_LIKE);
        }
        likeService.cancelLike(new LikeDTO().setUid(uid).setType(LikeEnum.COMMENT.ordinal()).setIdentifier(id));
        Comment comment = getById(id);
        comment.setLikeCount(comment.getLikeCount() - 1);
        return updateById(comment);
    }

    /**
     * 构建评论 VO
     *
     * @param obj OBJ
     * @return {@link CommentVO}
     */
    private CommentVO buildVO(Object obj) {
        Comment comment;
        if(obj instanceof Comment c){
            comment = c;
        } else {
            throw new CustomException(CodeEnum.UNSUPPORT_TYPE);
        }
        CommentVO vo = new CommentVO();
        BeanUtils.copyProperties(comment, vo);

        // 查询用户信息
        User user = userService.getById(comment.getUserId());
        if(user != null) {
            vo.setAvatar(user.getAvatar()).setUsername(user.getUsername());
        }

        if(!StringUtils.hasText(vo.getUsername())) {
            vo.setUsername(randomUtil.getRandomName(vo.getUserId().toString()));
        }

        if(vo.getParentUserId() != null) {
            User parentUser = redisUtil.get(RedisConstants.USER.getKey() + vo.getParentUserId(), User.class);
            if(parentUser != null) {
                vo.setParentUsername(parentUser.getUsername());
            }
            if(!StringUtils.hasText(vo.getParentUsername())) {
                vo.setParentUsername(randomUtil.getRandomName(vo.getParentUserId().toString()));
            }
        }
        return vo;
    }
}




