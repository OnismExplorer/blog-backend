package com.code.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.constants.CodeEnum;
import com.code.blog.constants.LikeEnum;
import com.code.blog.entity.Article;
import com.code.blog.entity.Like;
import com.code.blog.entity.WeiYan;
import com.code.blog.entity.dto.LikeDTO;
import com.code.blog.exception.CustomException;
import com.code.blog.service.LikeService;
import com.code.blog.service.WeiYanService;
import com.code.blog.mapper.WeiYanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author HeXin
* @description 针对表【wei_yan(微言表)】的数据库操作Service实现
* @createDate 2024-03-09 10:39:33
*/
@Service
public class WeiYanServiceImpl extends ServiceImpl<WeiYanMapper, WeiYan>
    implements WeiYanService{

    @Autowired
    private LikeService likeService;

    @Override
    public Boolean like(Long id) {
        // 获取当前登录用户的id
        Long uid = StpUtil.getLoginIdAsLong();
        WeiYan weiYan = lambdaQuery().eq(WeiYan::getId, id).one();
        if(weiYan == null) {
            throw new CustomException(CodeEnum.NOT_FOUND_WEIYAN);
        }
        Like like = likeService.get(uid, LikeEnum.WEIYAN.ordinal(), id);
        if(like != null){
            return cancelLike(id);
        }
        likeService.like(new LikeDTO().setUid(uid).setType(LikeEnum.WEIYAN.ordinal()).setIdentifier(id));
        weiYan.setLikeCount(weiYan.getLikeCount() + 1);
        return updateById(weiYan);
    }

    /**
     * 取消点赞
     *
     * @param id 文章id
     * @return {@link Boolean}
     */
    private Boolean cancelLike(Long id) {
        Long uid = StpUtil.getLoginIdAsLong();
        Like like = likeService.get(uid, LikeEnum.WEIYAN.ordinal(), id);
        if(like == null) {
            throw new CustomException(CodeEnum.NOT_FOUND_LIKE);
        }
        likeService.cancelLike(new LikeDTO().setUid(uid).setType(LikeEnum.WEIYAN.ordinal()).setIdentifier(id));
        WeiYan weiYan = getById(id);
        weiYan.setLikeCount(weiYan.getLikeCount() - 1);
        return updateById(weiYan);
    }

}




