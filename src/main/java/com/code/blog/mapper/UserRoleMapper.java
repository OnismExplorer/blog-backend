package com.code.blog.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.blog.entity.UserRole;

import java.util.List;

/**
 * 用户角色映射器
 *
 * @author HeXin
 * @description 针对表【user_role】的数据库操作Mapper
 * @createDate 2024-03-08 21:08:14
 * @Entity com.code.template.entity.UserRole
 * @date 2024/03/08
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 按用户 ID 选择角色名称
     * @param id 编号
     * @return {@link List}<{@link String}>
     */
    List<String> selectRoleNamesByUserId(Long id);
}




