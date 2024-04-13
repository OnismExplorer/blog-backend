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
 * 资源信息
 * @author HeXin
 * @TableName resource
 */
@TableName(value ="resource")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Resource extends BaseEntity implements Serializable {
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
     * 资源类型
     */
    @TableField(value = "type")
    @Schema(description = "资源类型")
    private String type;

    /**
     * 资源路径
     */
    @TableField(value = "path")
    @Schema(description = "资源路径(放上传文件成功后返回的url/网络上的url)")
    private String path;

    /**
     * 资源内容的大小，单位：字节
     */
    @TableField(value = "size")
    @Schema(description = "资源内容的大小，单位：字节")
    private Integer size;

    /**
     * 资源的 MIME 类型
     */
    @TableField(value = "mime_type")
    @Schema(description = "资源的 MIME 类型")
    private String mimeType;

    /**
     * 是否启用[0:否，1:是]
     */
    @TableField(value = "status")
    @Schema(description = "是否启用[0:否，1:是]")
    private Integer status;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}