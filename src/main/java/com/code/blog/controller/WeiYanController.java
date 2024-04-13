package com.code.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.code.blog.common.Result;
import com.code.blog.constants.CodeEnum;
import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.WeiYan;
import com.code.blog.entity.dto.WeiYanDTO;
import com.code.blog.exception.CustomException;
import com.code.blog.service.WeiYanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 微言控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="微言模版")
@RestController
@RequestMapping("/weiyan")
@SaCheckLogin
public class WeiYanController extends BaseController<WeiYanService, WeiYan, WeiYanDTO,Long> {

    @Autowired
    private WeiYanService weiYanService;
    @Operation(summary = "微言点赞/取消点赞",description = "传入的为文章id")
    @SaCheckLogin
    @PatchMapping("/like")
    public Result<String> like(@RequestParam Long id){
        return Result.isSuccess(weiYanService.like(id));
    }

    @Override
    @Operation(summary = "新增微言")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody WeiYanDTO instance) {
        if (!StringUtils.hasText(instance.getContent())) {
            return Result.fail(290,"微言不能为空！");
        }

        String content = Jsoup.clean(instance.getContent(), Safelist.basic());
        if(!StringUtils.hasText(content)) {
            throw new CustomException(CodeEnum.COMMENT_NOT_COMPLIANCE);
        }
        instance.setContent(content);
        return super.save(instance);
    }

    @Override
    protected Class<WeiYan> createInstance() {
        return WeiYan.class;
    }
}
