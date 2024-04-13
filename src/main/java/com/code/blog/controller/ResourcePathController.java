package com.code.blog.controller;

import com.code.blog.common.Result;
import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.ResourcePath;
import com.code.blog.entity.dto.ResourcePathDTO;
import com.code.blog.service.ResourcePathService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源路径控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="资源路径模块")
@RestController
@RequestMapping("/resourcePath")
public class ResourcePathController extends BaseController<ResourcePathService, ResourcePath, ResourcePathDTO,Long> {

    @Override
    @Operation(summary = "保存资源路径")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ResourcePathDTO instance) {
        if (!StringUtils.hasText(instance.getTitle()) || !StringUtils.hasText(instance.getType())) {
            return Result.fail(290,"标题和资源类型不能为空！");
        }
        return super.save(instance);
    }

    @Override
    protected Class<ResourcePath> createInstance() {
        return ResourcePath.class;
    }
}
