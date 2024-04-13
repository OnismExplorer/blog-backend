package com.code.blog.controller;

import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.Label;
import com.code.blog.entity.dto.LabelDTO;
import com.code.blog.service.LabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="文章标签模块")
@RestController
@RequestMapping("/label")
public class LabelController extends BaseController<LabelService,Label, LabelDTO,Long> {

    @Override
    protected Class<Label> createInstance() {
        return Label.class;
    }
}
