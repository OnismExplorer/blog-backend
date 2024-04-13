package com.code.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.constants.CodeEnum;
import com.code.blog.constants.RedisConstants;
import com.code.blog.entity.Like;
import com.code.blog.entity.dto.LikeDTO;
import com.code.blog.exception.CustomException;
import com.code.blog.service.LikeService;
import com.code.blog.mapper.LikeMapper;
import com.code.blog.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞服务实现类
 *
 * @author HeXin
 * @description 针对表【like(点赞记录表)】的数据库操作Service实现
 * @createDate 2024-03-10 10:33:57
 * @date 2024/03/10
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like>
    implements LikeService{

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Like get(Long uid, Integer type, Long identifier) {
        return lambdaQuery().eq(Like::getUid, uid)
                .eq(Like::getType, type)
                .eq(Like::getIdentifier,identifier).one();
    }

    @Override
    public void like(LikeDTO like) {
        // 查询缓存
        String str = redisUtil.getString(RedisConstants.LIKE.getKey() + like.getUid() + like.getType() + like.getIdentifier());
        if(StringUtils.isNotBlank(str)) {
            throw new CustomException(CodeEnum.OVERSPEED);
        }
        Long count = lambdaQuery().eq(Like::getUid, like.getUid())
                .eq(Like::getType, like.getType())
                .eq(Like::getIdentifier, like.getIdentifier())
                .count();
        if(count > 0){
            // 该用户已经点过赞
            cancelLike(like);
        }
        // 点赞信息暂时存入缓存，防止恶意刷库
        redisUtil.set(RedisConstants.LIKE, like.getUid().toString()+like.getType()+like.getIdentifier(), like);
        // 记录点赞信息
        save(like.toPo(Like.class));
    }

    @Override
    public void cancelLike(LikeDTO like) {
        Like one = lambdaQuery().eq(Like::getUid, like.getUid())
                .eq(Like::getType, like.getType())
                .eq(Like::getIdentifier, like.getIdentifier()).one();
        if(one == null){
            throw new CustomException(CodeEnum.NOT_FOUND_LIKE);
        }
        removeById(one);
    }
}




