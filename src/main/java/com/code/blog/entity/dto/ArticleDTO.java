package com.code.blog.entity.dto;

import com.code.blog.entity.Article;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 文章dto
 *
 * @author HeXin
 * @date 2024/03/09
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ArticleDTO extends ConverEntity<Article> {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 分类ID
     */
    @Schema(description = "分类ID(一篇文章最多属于三个分类)")
    private List<Long> sortId;

    /**
     * 标签ID
     */
    @Schema(description = "标签ID(一篇文章最多五个标签)")
    private List<Long> labelId;

    /**
     * 封面
     */
    @Schema(description = "封面")
    private String articleCover;

    /**
     * 博文标题
     */
    @Schema(description = "博文标题")
    private String articleTitle;

    /**
     * 博文内容
     */
    @Schema(description = "博文内容")
    private String articleContent;

    /**
     * 是否可见[0:否，1:是]
     */
    @Schema(description = "是否可见[0:否，1:是]")
    private Integer viewStatus;

    /**
     * 密码
     */
    @Schema(description = "文章密码")
    private String password;

    /**
     * 是否推荐[0:否，1:是]
     */
    @Schema(description = "是否推荐[0:否，1:是]")
    private Integer recommendStatus;

    /**
     * 是否启用评论[0:否，1:是]
     */
    @Schema(description = "是否启用评论[0:否，1:是]")
    private Integer commentStatus;

}
