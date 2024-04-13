package com.code.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.entity.UserRole;
import com.code.blog.mapper.UserRoleMapper;
import com.code.blog.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色服务实现类
 *
 * @author HeXin
 * @description 针对表【user_role】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {
    /**
     * 解除该用户此角色绑定
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @return {@link Boolean}
     */
    @Override
    public Boolean unbind(Long userId, Long roleId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userId).eq(UserRole::getRoleId,roleId);
        return remove(wrapper);
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long uid) {
        return lambdaQuery().eq(UserRole::getUserId, uid).select(UserRole::getRoleId).list()
                .stream().map(obj -> Long.parseLong(obj.toString())).toList();
    }

    @Override
    public List<String> getRoleNamesByUserId(Long id) {
        return baseMapper.selectRoleNamesByUserId(id);
    }
}




