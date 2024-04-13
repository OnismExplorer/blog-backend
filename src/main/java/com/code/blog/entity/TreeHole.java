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
 * 树洞
 * @author HeXin
 * @TableName tree_hole
 */
@TableName(value ="tree_hole")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TreeHole extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @Schema(description = "头像")
    private String avatar;

    /**
     * 留言
     */
    @TableField(value = "message")
    @Schema(description = "留言")
    private String message;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}