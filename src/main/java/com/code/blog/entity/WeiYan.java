package com.code.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.code.blog.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 微言表
 * @author HeXin
 * @TableName wei_yan
 */
@TableName(value ="wei_yan")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class WeiYan extends BaseEntity implements Serializable {
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
     * 点赞数
     */
    @TableField(value = "like_count")
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 内容
     */
    @TableField(value = "content")
    @Schema(description = "内容")
    private String content;

    /**
     * 类型
     */
    @TableField(value = "type")
    @Schema(description = "类型")
    private String type;

    /**
     * 是否公开[0:仅自己可见，1:所有人可见]
     */
    @TableField(value = "is_public")
    @Schema(description = "是否公开[0:仅自己可见，1:所有人可见]")
    private Integer isPublic;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}