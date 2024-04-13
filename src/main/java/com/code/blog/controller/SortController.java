package com.code.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.code.blog.common.Result;
import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.Sort;
import com.code.blog.entity.dto.SortDTO;
import com.code.blog.entity.vo.SortVO;
import com.code.blog.service.SortService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这种控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="文章分类模块")
@RestController
@RequestMapping("/sort")
public class SortController extends BaseController<SortService, Sort, SortDTO,Long> {

    @Autowired
    private SortService sortService;

    @Override
    @Operation(summary = "查询分类列表(后台)")
    @GetMapping("/admin/list")
    @SaCheckPermission("admin.sort.list")
    public Result<List<Sort>> list() {
        return super.list();
    }

    @Operation(summary = "查询分类列表(前台)")
    @GetMapping("/list")
    public Result<List<SortVO>> listFront(@Schema(description = "是否显示其标签，0不显示，1显示") @RequestParam(required = false,defaultValue = "0") Integer isShow) {
        return Result.success(sortService.listFront(isShow));
    }

    /**
     * 获取标签
     *
     * @param id 编号
     * @return {@link Result}<{@link SortVO}>
     */
    @Operation(summary = "获取标签",description = "返回的是一个分类对象，传参为分类id")
    @GetMapping("/get/labels")
    public Result<SortVO> getLabels(@RequestParam Long id){
        return Result.success(sortService.getLabels(id));
    }
    @Override
    protected Class<Sort> createInstance() {
        return Sort.class;
    }
}
