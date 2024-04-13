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
 * 点赞记录表
 * @TableName like
 */
@TableName(value ="`like`")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Like extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "id")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    @Schema(description = "用户id")
    private Long uid;

    /**
     * 点赞类型(0：文章点赞，1：评论点赞，2：微言点赞)
     */
    @TableField(value = "`type`")
    @Schema(description = "点赞类型(0：文章点赞，1：评论点赞，2：微言点赞)")
    private Integer type;

    /**
     * 点赞标识符(文章/评论/微言的id)
     */
    @TableField(value = "`identifier`")
    @Schema(description = "点赞标识符(文章/评论/微言的id)")
    private Long identifier;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}