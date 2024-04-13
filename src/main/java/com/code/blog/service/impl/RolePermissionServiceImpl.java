package com.code.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.entity.RolePermission;
import com.code.blog.mapper.RolePermissionMapper;
import com.code.blog.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限服务实现类
 *
 * @author HeXin
 * @description 针对表【role_permission】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService {
    @Override
    public List<String> getPermissionNameByRole(Long roleId) {
        return baseMapper.selectPermissionNameByRole(roleId);
    }

    @Override
    public List<Long> getPermissionIdsByRole(List<Long> roleIds) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(RolePermission::getPermissionId).in(RolePermission::getRoleId,roleIds);
        return baseMapper.selectObjs(wrapper)
                .stream().map(o -> Long.parseLong(o.toString()))
                .toList();
    }

    @Override
    public Boolean unbind(Long roleId, Long permissionId) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId).eq(RolePermission::getPermissionId,permissionId);
        return remove(wrapper);
    }
}




