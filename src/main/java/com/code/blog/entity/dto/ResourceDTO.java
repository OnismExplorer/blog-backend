package com.code.blog.entity.dto;

import com.code.blog.entity.Resource;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资源dto
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ResourceDTO extends ConverEntity<Resource> {
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
     * 资源类型
     */
    @Schema(description = "资源类型")
    private String type;

    /**
     * 资源路径
     */
    @Schema(description = "资源路径")
    private String path;

    /**
     * 资源内容的大小，单位：字节
     */
    @Schema(description = "资源内容的大小，单位：字节")
    private Integer size;

    /**
     * 资源的 MIME 类型
     */
    @Schema(description = "资源的 MIME 类型")
    private String mimeType;

    /**
     * 是否启用[0:否，1:是]
     */
    @Schema(description = "是否启用[0:否，1:是]")
    private Integer status;
}
