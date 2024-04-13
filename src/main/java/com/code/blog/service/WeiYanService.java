package com.code.blog.service;

import com.code.blog.entity.WeiYan;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 微言服务
 *
 * @author HeXin
 * @description 针对表【wei_yan(微言表)】的数据库操作Service
 * @createDate 2024-03-09 10:39:33
 * @date 2024/03/12
 */
public interface WeiYanService extends IService<WeiYan> {

    /**
     * 微言点赞
     *
     * @param id 编号
     * @return {@link Boolean}
     */
    Boolean like(Long id);
}
