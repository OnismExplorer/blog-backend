package com.code.blog.entity.dto;

import com.code.blog.entity.TreeHole;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 树洞DTO
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TreeHoleDTO extends ConverEntity<TreeHole> {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 留言
     */
    @Schema(description = "留言")
    private String message;
}
