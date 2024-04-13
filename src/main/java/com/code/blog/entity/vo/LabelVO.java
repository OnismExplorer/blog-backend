package com.code.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 标签 VO
 *
 * @author HeXin
 * @date 2024/03/12
 */
@Data
@Accessors(chain = true)
public class LabelVO {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

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
