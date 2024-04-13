package com.code.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import com.code.blog.common.PageInfo;
import com.code.blog.common.Result;
import com.code.blog.entity.Comment;
import com.code.blog.entity.dto.CommentDTO;
import com.code.blog.entity.vo.CommentVO;
import com.code.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name= "评论模块")
@SaCheckLogin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "保存评论",description = "id不用填写，直接删除该行")
    @PostMapping("/save")
    public Result<String> save(@RequestBody CommentDTO commentDTO){
        return Result.isSuccess(commentService.save(commentDTO));
    }

    @Operation(summary = "删除评论",description = "将其子评论一起删除")
    @SaCheckPermission("admin.comment.delete")
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam("id") Long id){
        return Result.isSuccess(commentService.delete(id));
    }

    @Operation(summary = "获取评论数量",description = "source:评论对象id，type:评论对象类型")
    @GetMapping("/count")
    public Result<Integer> count(@RequestParam Long source,@RequestParam String type){
        return Result.success(commentService.count(source,type));
    }

    @Operation(summary = "查询评论列表(后台)",description = "resource:评论对象id，type:评论对象类型")
    @SaCheckPermission("admin.comment.list")
    @GetMapping("/admin/list")
    public Result<PageInfo<Comment>> list(@RequestParam(required = false) Long uid, @RequestParam(required = false) Long resource, @RequestParam(required = false) String type,
                                            @RequestParam(required = false,defaultValue = "1") Long pageNum,
                                            @RequestParam(required = false,defaultValue = "10") Long pageSize){
        return Result.page(commentService.list(uid,resource,type,pageNum,pageSize));
    }

    @Operation(summary = "查询评论列表(前台)",description = "固定为8条一页")
    @PostMapping("/list")
    @SaIgnore
    public Result<PageInfo<CommentVO>> list(@RequestBody CommentDTO dto){
        return Result.page(commentService.list(dto));
    }

    @Operation(summary = "根据id获取评论",description = "id:评论id")
    @GetMapping("/get")
    public Result<Comment> get(@RequestParam Long id){
        return Result.success(commentService.getById(id));
    }

    @Operation(summary = "点赞/取消点赞评论")
    @PatchMapping("/like")
    public Result<String> like(@RequestParam Long id) {
        return Result.isSuccess(commentService.like(id));
    }
}
