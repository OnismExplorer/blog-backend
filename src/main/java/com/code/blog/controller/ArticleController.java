package com.code.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.blog.common.PageInfo;
import com.code.blog.common.Result;
import com.code.blog.constants.CodeEnum;
import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.Article;
import com.code.blog.entity.dto.ArticleDTO;
import com.code.blog.exception.CustomException;
import com.code.blog.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="文章模块")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<ArticleService,Article, ArticleDTO,Long> {

    @Autowired
    private ArticleService articleService;

    @Override
    @Operation(summary = "根据id获取文章",description = "传入的为文章id")
    public Result<Article> get(Long id) {
        Article article = articleService.getById(id);
        article.setViewCount(article.getViewCount()+1);
        articleService.updateById(article);
        return Result.success(article);
    }

    @Operation(summary = "文章点赞/取消点赞",description = "传入的为文章id")
    @SaCheckLogin
    @PatchMapping("/like")
    public Result<String> like(@RequestParam Long id){
        return Result.isSuccess(articleService.like(id));
    }

    @Operation(summary = "文章分页查询",description = "key为搜索关键词，type为排序规则，默认为-1(按创建时间倒序)，0为创建时间升序，1为阅读量降序，2为点赞数降序")
    @GetMapping("/page")
    public Result<PageInfo<Article>> page(@RequestParam(required = false,defaultValue = "1") Long pageNum,
                                          @RequestParam(required = false,defaultValue = "10") Long pageSize,
                                          @RequestParam(required = false)String key,
                                          @RequestParam(required = false,defaultValue = "-1")Integer type){
        LambdaQueryChainWrapper<Article> wrapper = articleService.lambdaQuery().like(StringUtils.isNotBlank(key), Article::getArticleTitle, key)
                .or()
                .like(StringUtils.isNotBlank(key), Article::getArticleContent, key);
        switch (type) {
            case -1 -> wrapper.orderByDesc(Article::getGmtCreate);
            case 0 -> wrapper.orderByAsc(Article::getGmtCreate);
            case 1 -> wrapper.orderByDesc(Article::getViewCount);
            case 2 -> wrapper.orderByDesc(Article::getLikeCount);
            default ->
                // 处理未知的 type，可以选择抛出异常或采取其他处理方式
                    throw new CustomException(CodeEnum.PARAMETER_ERROR);
        }
        return Result.page(wrapper.page(new Page<>(pageNum, pageSize)));
    }
    @Override
    protected Class<Article> createInstance() {
        return Article.class;
    }
}
