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
 * 资源(收藏)路径
 * @author HeXin
 * @TableName resource_path
 */
@TableName(value ="resource_path")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ResourcePath extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    @Schema(description = "标题")
    private String title;

    /**
     * 分类
     */
    @TableField(value = "classify")
    @Schema(description = "分类")
    private String classify;

    /**
     * 封面
     */
    @TableField(value = "cover")
    @Schema(description = "封面")
    private String cover;

    /**
     * 链接
     */
    @TableField(value = "url")
    @Schema(description = "链接")
    private String url;

    /**
     * 简介
     */
    @TableField(value = "introduction")
    @Schema(description = "简介")
    private String introduction;

    /**
     * 资源类型
     */
    @TableField(value = "type")
    @Schema(description = "资源类型")
    private String type;

    /**
     * 是否启用[0:否，1:是]
     */
    @TableField(value = "status")
    @Schema(description = "是否启用[0:否，1:是]")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @Schema(description = "备注")
    private String remark;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}