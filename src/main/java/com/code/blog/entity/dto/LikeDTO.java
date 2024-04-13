package com.code.blog.entity.dto;

import com.code.blog.entity.Like;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 点赞dto
 *
 * @author HeXin
 * @date 2024/03/10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class LikeDTO extends ConverEntity<Like> {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long uid;

    /**
     * 点赞标识符(文章/评论/微言的id)
     */
    @Schema(description = "点赞标识符(文章/评论/微言的id)")
    private Long identifier;

    /**
     * 点赞类型(0：文章点赞，1：评论点赞，2：微言点赞)
     */
    @Schema(description = "点赞类型(0：文章点赞，1：评论点赞，2：微言点赞)")
    private Integer type;
}
