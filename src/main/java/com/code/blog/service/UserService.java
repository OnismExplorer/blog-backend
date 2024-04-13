package com.code.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.code.blog.entity.User;
import com.code.blog.entity.dto.LoginParam;
import com.code.blog.entity.dto.UserDTO;

/**
 * 用户服务
 *
 * @author HeXin
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface UserService extends IService<User> {

    /**
     * 注册
     *
     * @param dto  dto
     * @param code 代码
     * @return {@link Boolean}
     */
    Boolean regist(UserDTO dto, String code);

    /**
     * 登录
     *
     * @param param 参数
     * @return {@link String}
     */
    String login(LoginParam param);

    /**
     * 按名称获取用户
     *
     * @param username 用户名
     * @return {@link User}
     */
    User getUserByName(String username);
    /**
     * 绑定角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return {@link Boolean}
     */
    Boolean bindRole(Long userId, Long roleId);

    /**
     * 解除角色绑定
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return {@link Boolean}
     */
    Boolean unbindRole(Long userId, Long roleId);

    /**
     * 附加信息
     *
     * @param user 用户
     * @return {@link User}
     */
    User appendInfo(User user);
}
