package com.code.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.code.blog.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 文章表
 * @author HeXin
 * @TableName article
 */
@TableName(value ="article")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Article extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 分类ID
     */
    @TableField(value = "sort_id",typeHandler = JacksonTypeHandler.class)
    @Schema(description = "分类ID(一篇文章最多属于三个分类)")
    private List<Long> sortId;

    /**
     * 标签ID
     */
    @TableField(value = "label_id",typeHandler = JacksonTypeHandler.class)
    @Schema(description = "标签ID(一篇文章最多五个标签)")
    private List<Long> labelId;

    /**
     * 封面
     */
    @TableField(value = "article_cover")
    @Schema(description = "封面")
    private String articleCover;

    /**
     * 博文标题
     */
    @TableField(value = "article_title")
    @Schema(description = "博文标题")
    private String articleTitle;

    /**
     * 博文内容
     */
    @TableField(value = "article_content")
    @Schema(description = "博文内容")
    private String articleContent;

    /**
     * 浏览量
     */
    @TableField(value = "view_count")
    @Schema(description = "浏览量")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @TableField(value = "like_count")
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 是否可见[0:否，1:是]
     */
    @TableField(value = "view_status")
    @Schema(description = "是否可见[0:否，1:是]")
    private Integer viewStatus;

    /**
     * 密码
     */
    @TableField(value = "password")
    @Schema(description = "密码")
    private String password;

    /**
     * 是否推荐[0:否，1:是]
     */
    @TableField(value = "recommend_status")
    @Schema(description = "是否推荐[0:否，1:是]")
    private Integer recommendStatus;

    /**
     * 是否启用评论[0:否，1:是]
     */
    @TableField(value = "comment_status")
    @Schema(description = "是否启用评论[0:否，1:是]")
    private Integer commentStatus;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}