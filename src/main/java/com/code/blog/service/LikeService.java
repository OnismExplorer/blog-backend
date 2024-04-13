package com.code.blog.service;

import com.code.blog.entity.Like;
import com.baomidou.mybatisplus.extension.service.IService;
import com.code.blog.entity.dto.LikeDTO;

/**
 * 点赞服务
 *
 * @author HeXin
 * @description 针对表【like(点赞记录表)】的数据库操作Service
 * @createDate 2024-03-10 10:33:57
 * @date 2024/03/10
 */
public interface LikeService extends IService<Like> {

    /**
     * 获取记录
     *
     * @param uid        uid
     * @param type       类型
     * @param identifier 标识符
     * @return {@link Like}
     */
    Like get(Long uid,Integer type,Long identifier);
    /**
     * 点赞
     *
     * @param like 喜欢
     */
    void like(LikeDTO like);

    /**
     * 取消点赞
     *
     * @param like 喜欢
     */
    void cancelLike(LikeDTO like);
}
