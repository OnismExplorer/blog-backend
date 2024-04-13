package com.code.blog.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.blog.entity.RolePermission;

import java.util.List;

/**
 * 角色权限映射器
 *
 * @author HeXin
 * @description 针对表【role_permission】的数据库操作Mapper
 * @createDate 2024-03-08 21:08:14
 * @Entity com.code.template.entity.RolePermission
 * @date 2024/03/08
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    /**
     * 按角色选择权限名称
     *
     * @param roleId 角色 ID
     * @return {@link List}<{@link String}>
     */
    List<String> selectPermissionNameByRole(Long roleId);
}




