package com.code.blog.entity.dto;

import com.code.blog.entity.WeiYan;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 微言DTO
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WeiYanDTO extends ConverEntity<WeiYan> {
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
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;

    /**
     * 是否公开[0:仅自己可见，1:所有人可见]
     */
    @Schema(description = "是否公开[0:仅自己可见，1:所有人可见]")
    private Integer isPublic;
}
