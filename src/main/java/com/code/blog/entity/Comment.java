package com.code.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import com.code.blog.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 文章评论表
 * @author HeXin
 * @TableName comment
 */
@TableName(value ="comment")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 评论来源标识(若为文章评论则为文章id)
     */
    @TableField(value = "source")
    @Schema(description = "评论来源标识(若为文章评论则为文章id)")
    private Long source;

    /**
     * 评论来源类型
     */
    @TableField(value = "type")
    @Schema(description = "评论来源类型")
    private String type;

    /**
     * 父评论ID
     */
    @TableField(value = "parent_comment_id")
    @Schema(description = "父评论ID")
    private Long parentCommentId;

    /**
     * 发表用户ID
     */
    @TableField(value = "user_id")
    @Schema(description = "发表用户ID")
    private Long userId;

    /**
     * 楼层评论ID
     */
    @TableField(value = "floor_comment_id")
    @Schema(description = "楼层评论ID")
    private Long floorCommentId;

    /**
     * 父发表用户名ID
     */
    @TableField(value = "parent_user_id")
    @Schema(description = "父发表用户名ID")
    private Long parentUserId;

    /**
     * 点赞数
     */
    @TableField(value = "like_count")
    @Schema(description = "点赞数")
    private Long likeCount;

    /**
     * 评论内容
     */
    @TableField(value = "comment_content")
    @Schema(description = "评论内容")
    private String commentContent;

    /**
     * 评论额外信息
     */
    @TableField(value = "comment_info")
    @Schema(description = "评论额外信息")
    private String commentInfo;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}