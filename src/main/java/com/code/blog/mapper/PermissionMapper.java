package com.code.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.blog.entity.Permission;

import java.util.List;

/**
 * 许可映射器
 *
 * @author HeXin
 * @description 针对表【permission】的数据库操作Mapper
 * @createDate 2024-03-08 21:08:14
 * @Entity com.code.template.entity.Permission
 * @date 2024/03/08
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 获取子节点
     *
     * @param parentId 父 ID
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> getChildren(String parentId);

    /**
     * 按用户获取权限
     *
     * @param id 编号
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> getPermissionByUser(Long id);
}




