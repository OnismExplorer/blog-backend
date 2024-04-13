package com.code.blog.service;

import com.code.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author HeXin
* @description 针对表【article(文章表)】的数据库操作Service
* @createDate 2024-03-09 10:39:33
*/
public interface ArticleService extends IService<Article> {

    /**
     * 文章点赞
     *
     * @param id 文章id
     * @return {@link Boolean}
     */
    Boolean like(Long id);
}
