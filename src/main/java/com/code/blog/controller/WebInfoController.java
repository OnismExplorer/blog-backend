package com.code.blog.controller;

import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.WebInfo;
import com.code.blog.entity.dto.WebInfoDTO;
import com.code.blog.service.WebInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web信息控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="网站信息控制器")
@RestController
@RequestMapping("/webInfo")
public class WebInfoController extends BaseController<WebInfoService, WebInfo, WebInfoDTO,Long> {
    @Override
    protected Class<WebInfo> createInstance() {
        return  WebInfo.class;
    }
}
