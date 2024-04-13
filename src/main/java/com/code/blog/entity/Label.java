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
 * 标签
 * @author HeXin
 * @TableName label
 */
@TableName(value ="label")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Label extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 分类ID
     */
    @TableField(value = "sort_id")
    @Schema(description = "分类ID")
    private Long sortId;

    /**
     * 标签名称
     */
    @TableField(value = "label_name")
    @Schema(description = "标签名称")
    private String labelName;

    /**
     * 标签描述
     */
    @TableField(value = "label_description")
    @Schema(description = "标签描述")
    private String labelDescription;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}