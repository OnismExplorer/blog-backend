package com.code.blog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.constants.RedisConstants;
import com.code.blog.entity.Permission;
import com.code.blog.entity.RolePermission;
import com.code.blog.exception.CustomException;
import com.code.blog.mapper.PermissionMapper;
import com.code.blog.service.PermissionService;
import com.code.blog.service.RolePermissionService;
import com.code.blog.utils.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限服务实现类
 *
 * @author HeXin
 * @description 针对表【permission】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> getChildren(String parentId) {
        return baseMapper.getChildren(parentId);
    }

    @Override
    public List<Long> getPermissionByRole(Long roleId) {
        List<Long> permissionIdList = rolePermissionService.lambdaQuery()
                .select(RolePermission::getPermissionId)
                // 此时得到的是权限对象的集合
                .eq(RolePermission::getRoleId, roleId).list()
                .stream().map(RolePermission::getPermissionId).toList();
        if(permissionIdList.isEmpty()){
            return Collections.emptyList();
        }
        return lambdaQuery().in(Permission::getId,permissionIdList)
                .list().stream()
                .map(Permission::getId).toList();
    }

    @Override
    @Cache(constants = RedisConstants.USER_PERMISSION)
    public List<String> getPermissionByUser(Long id) {
        List<Permission> allPermissions = list();
        List<Permission> permissions = baseMapper.getPermissionByUser(id);
        return getSubPermissions(allPermissions,permissions).stream().map(Permission::getKeyName).toList();
    }

    /**
     * 获取子权限
     *
     * @param allPermissions    所有权限
     * @param parentPermissions 父权限
     * @return {@link Set}<{@link Permission}>
     */
    public static Set<Permission> getSubPermissions(List<Permission> allPermissions, List<Permission> parentPermissions){
        Set<Permission> subPermissions = new HashSet<>();
        for (Permission parentPermission : parentPermissions) {
            subPermissions.add(parentPermission);
            getSubPermissionRecursively(allPermissions, parentPermission.getId(), subPermissions);
        }
        return subPermissions;
    }

    /**
     * 递归获取子权限
     *
     * @param allPermissions 所有权限
     * @param parentId       父 ID
     * @param subPermissions 子权限
     */
    public static void getSubPermissionRecursively(List<Permission> allPermissions, Long parentId, Set<Permission> subPermissions){
        for (Permission permission : allPermissions) {
            if(permission.getParentId().equals(parentId)){
                subPermissions.add(permission);
                getSubPermissionRecursively(allPermissions, permission.getId(), subPermissions);
            }
        }
    }

    @Override
    public Boolean removePermission(Long id) {
        Long count = lambdaQuery().eq(Permission::getParentId, id).count();
        if(count > 0){
            throw new CustomException("存在子权限，无法删除");
        }
        return removeById(id);
    }
}




