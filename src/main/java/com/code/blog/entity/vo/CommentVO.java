package com.code.blog.entity.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.blog.entity.Comment;
import com.code.blog.entity.base.ConverEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 评论 VO
 *
 * @author HeXin
 * @date 2024/03/10
 */
@Data
@Accessors(chain = true)
public class CommentVO extends ConverEntity<Comment> {
    private Long id;

    @NotNull(message = "评论来源标识不能为空")
    private Long source;

    /**
     * 评论来源类型
     */
    @NotBlank(message = "评论来源类型不能为空")
    private String type;

    //层主的parentCommentId是0，回复的parentCommentId是层主的id
    private Long parentCommentId;

    //层主的parentUserId是null，回复的parentUserId是被回复的userId
    private Long parentUserId;

    private Long userId;

    private Long likeCount;

    @NotBlank(message = "评论内容不能为空")
    private String commentContent;

    private String commentInfo;

    //子评论必须传评论楼层ID
    private Long floorCommentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    // 需要查询封装
    private Page<CommentVO> childComments;
    private String parentUsername;
    private String username;
    private String avatar;
}
