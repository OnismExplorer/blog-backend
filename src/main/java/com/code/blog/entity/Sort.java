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
 * 分类
 * @author HeXin
 * @TableName sort
 */
@TableName(value ="sort")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Sort extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 分类名称
     */
    @TableField(value = "sort_name")
    @Schema(description = "分类名称")
    private String sortName;

    /**
     * 分类描述
     */
    @TableField(value = "sort_description")
    @Schema(description = "分类描述")
    private String sortDescription;

    /**
     * 分类类型[0:导航栏分类，1:普通分类]
     */
    @TableField(value = "sort_type")
    @Schema(description = "分类类型[0:导航栏分类，1:普通分类]")
    private Integer sortType;

    /**
     * 导航栏分类优先级：数字小的在前面
     */
    @TableField(value = "priority")
    @Schema(description = "导航栏分类优先级：数字小的在前面")
    private Integer priority;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}