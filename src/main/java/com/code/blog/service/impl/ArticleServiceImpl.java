package com.code.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.constants.CodeEnum;
import com.code.blog.constants.LikeEnum;
import com.code.blog.entity.Article;
import com.code.blog.entity.Like;
import com.code.blog.entity.dto.LikeDTO;
import com.code.blog.exception.CustomException;
import com.code.blog.mapper.ArticleMapper;
import com.code.blog.service.ArticleService;
import com.code.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author HeXin
* @description 针对表【article(文章表)】的数据库操作Service实现
* @createDate 2024-03-09 10:39:33
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    private final LikeService likeService;

    @Autowired
    public ArticleServiceImpl(LikeService likeService) {
        this.likeService = likeService;
    }

    @Override
    public Boolean like(Long id) {
        // 获取当前登录用户的id
        Long uid = StpUtil.getLoginIdAsLong();
        Article article = lambdaQuery().eq(Article::getId, id).one();
        if(article == null) {
            throw new CustomException(CodeEnum.ARTICLE_NOT_FOUND);
        }
        Like like = likeService.get(uid, LikeEnum.ARTICLE.ordinal(), id);
        if(like != null){
            return cancelLike(id);
        }
        likeService.like(new LikeDTO().setUid(uid).setType(LikeEnum.ARTICLE.ordinal()).setIdentifier(id));
        article.setLikeCount(article.getLikeCount() + 1);
        return updateById(article);
    }

    /**
     * 取消点赞
     *
     * @param id 文章id
     * @return {@link Boolean}
     */
    private Boolean cancelLike(Long id) {
        Long uid = StpUtil.getLoginIdAsLong();
        Like like = likeService.get(uid, LikeEnum.ARTICLE.ordinal(), id);
        if(like == null) {
            throw new CustomException(CodeEnum.NOT_FOUND_LIKE);
        }
        likeService.cancelLike(new LikeDTO().setUid(uid).setType(LikeEnum.ARTICLE.ordinal()).setIdentifier(id));
        Article article = getById(id);
        article.setLikeCount(article.getLikeCount() - 1);
        return updateById(article);
    }
}




