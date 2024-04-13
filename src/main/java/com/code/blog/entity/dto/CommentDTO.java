package com.code.blog.entity.dto;


import com.code.blog.entity.Comment;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 评论dto
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CommentDTO extends ConverEntity<Comment> {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 评论来源标识(若为文章评论则为文章id)
     */
    @Schema(description = "评论来源标识(若为文章评论则为文章id)")
    private Long source;

    /**
     * 评论来源类型
     */
    @Schema(description = "评论来源类型")
    private String type;

    /**
     * 父评论ID
     */
    @Schema(description = "父评论ID")
    private Long parentCommentId;

    /**
     * 发表用户ID
     */
    @Schema(description = "发表用户ID")
    private Long userId;

    /**
     * 楼层评论ID
     */
    @Schema(description = "楼层评论ID")
    private Long floorCommentId;

    /**
     * 父发表用户名ID
     */
    @Schema(description = "父发表用户名ID")
    private Long parentUserId;

    /**
     * 评论内容
     */
    @Schema(description = "评论内容")
    private String commentContent;

    /**
     * 评论额外信息
     */
    @Schema(description = "评论额外信息")
    private String commentInfo;
}
