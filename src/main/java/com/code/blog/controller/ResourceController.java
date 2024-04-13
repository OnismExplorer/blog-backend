package com.code.blog.controller;

import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.Resource;
import com.code.blog.entity.dto.ResourceDTO;
import com.code.blog.service.ResourceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="资源模块")
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController<ResourceService, Resource, ResourceDTO,Long> {
    @Override
    protected Class<Resource> createInstance() {
        return Resource.class;
    }
}
