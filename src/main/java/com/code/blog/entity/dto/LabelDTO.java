package com.code.blog.entity.dto;

import com.code.blog.entity.Label;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 标签dto
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LabelDTO extends ConverEntity<Label> {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 分类ID
     */
    @Schema(description = "分类ID")
    private Long sortId;

    /**
     * 标签名称
     */
    @Schema(description = "标签名称")
    private String labelName;

    /**
     * 标签描述
     */
    @Schema(description = "标签描述")
    private String labelDescription;
}
