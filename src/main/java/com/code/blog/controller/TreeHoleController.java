package com.code.blog.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.RandomUtil;
import com.code.blog.common.Result;
import com.code.blog.controller.base.BaseController;
import com.code.blog.entity.TreeHole;
import com.code.blog.entity.dto.TreeHoleDTO;
import com.code.blog.service.TreeHoleService;
import com.code.blog.utils.RandomUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 树洞控制器
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Tag(name="树洞模块")
@RestController
@RequestMapping("/treeHole")
public class TreeHoleController extends BaseController<TreeHoleService, TreeHole, TreeHoleDTO,Long> {


    @Autowired
    private RandomUtils randomUtils;
    @Override
    @SaIgnore
    @Operation(description = "保存树洞信息")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody TreeHoleDTO instance) {
        if (!StringUtils.hasText(instance.getMessage())) {
            return Result.fail(292,"留言不能为空！");
        }
        if (!StringUtils.hasText(instance.getAvatar())) {
            instance.setAvatar(randomUtils.getRandomAvatar(String.valueOf(RandomUtil.randomInt(655635))));
        }
        return super.save(instance);
    }

    @Override
    protected Class<TreeHole> createInstance() {
        return TreeHole.class;
    }
}
