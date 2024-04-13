package com.code.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.code.blog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.code.blog.entity.dto.CommentDTO;
import com.code.blog.entity.vo.CommentVO;

/**
 * 评论服务
 *
 * @author HeXin
 * @description 针对表【comment(文章评论表)】的数据库操作Service
 * @createDate 2024-03-09 10:39:33
 * @date 2024/03/10
 */
public interface CommentService extends IService<Comment> {


    /**
     * 保存评论
     *
     * @param commentDTO 注释 DTO
     * @return {@link Boolean}
     */
    Boolean save(CommentDTO commentDTO);

    /**
     * 删除评论
     *
     * @param id id
     * @return {@link Boolean}
     */
    Boolean delete(Long id);

    /**
     * 评论数量
     *
     * @param source 源
     * @param type   类型
     * @return {@link Integer}
     */
    Integer count(Long source, String type);

    /**
     * 获取列表
     *
     * @param resource 资源id
     * @param type     类型
     * @param uid      UID
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return {@link IPage}<{@link Comment}>
     */
    IPage<Comment> list(Long uid, Long resource, String type, Long pageNum, Long pageSize);

    /**
     * 获取评论列表
     *
     * @param dto DTO
     * @return {@link IPage}<{@link Comment}>
     */
    IPage<CommentVO> list(CommentDTO dto);

    /**
     * 点赞评论
     *
     * @param id 编号
     * @return {@link Boolean}
     */
    Boolean like(Long id);
}
