package com.code.blog.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.code.blog.constants.RedisConstants;
import com.code.blog.service.PermissionService;
import com.code.blog.service.RoleService;
import com.code.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 鉴权接口实现类
 *
 * @author HeXin
 * @date 2024/03/08
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        long id = StpUtil.getLoginIdAsLong();
        List<String> permissions = permissionService.getPermissionByUser(id);
        if(permissions.isEmpty()){
            return Collections.emptyList();
        }
        redisUtil.set(RedisConstants.USER_PERMISSION.getKey()+id,permissions);
        return permissions;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roleNames =  roleService.getRoleNameByUser(Long.parseLong(loginId.toString()));
        if(CollUtil.isEmpty(roleNames)){
            return Collections.emptyList();
        }
        redisUtil.set(RedisConstants.USER_ROLE.getKey()+loginId,roleNames);
        return roleNames;
    }
}
