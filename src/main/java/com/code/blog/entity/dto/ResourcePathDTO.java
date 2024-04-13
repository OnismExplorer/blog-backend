package com.code.blog.entity.dto;

import com.code.blog.entity.ResourcePath;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资源路径dto
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ResourcePathDTO extends ConverEntity<ResourcePath> {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String classify;

    /**
     * 封面
     */
    @Schema(description = "封面")
    private String cover;

    /**
     * 链接
     */
    @Schema(description = "链接")
    private String url;

    /**
     * 简介
     */
    @Schema(description = "简介")
    private String introduction;

    /**
     * 资源类型
     */
    @Schema(description = "资源类型")
    private String type;

    /**
     * 是否启用[0:否，1:是]
     */
    @Schema(description = "是否启用[0:否，1:是]")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
