package com.code.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.constants.CodeEnum;
import com.code.blog.constants.RedisConstants;
import com.code.blog.entity.User;
import com.code.blog.entity.UserRole;
import com.code.blog.entity.dto.LoginParam;
import com.code.blog.entity.dto.UserDTO;
import com.code.blog.exception.CustomException;
import com.code.blog.mapper.UserMapper;
import com.code.blog.service.UserRoleService;
import com.code.blog.service.UserService;
import com.code.blog.utils.PasswordUtils;
import com.code.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author HeXin
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Boolean regist(UserDTO dto, String code) {
        // 从缓存中获取验证码
        if(redisUtil.getTime(RedisConstants.EMAIL_CODE.getKey() + dto.getEmail())==0){
            throw new CustomException(CodeEnum.CODE_EXPIRED);
        }
        if(!code.equals(redisUtil.getObject(RedisConstants.EMAIL_CODE.getKey() + dto.getEmail()).toString())){
            throw new CustomException(CodeEnum.CODE_ERROR);
        }
        Long count = lambdaQuery().eq(User::getUsername, dto.getUserName()).count();
        if(count > 0) {
            throw new CustomException(CodeEnum.USERNAME_EXIST);
        }
        User user = dto.toPo(User.class);
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        return save(user);
    }

    @Override
    public String login(LoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        User user = getUserByName(username);
        if(user == null){
            user = getUserByEmail(username);
            if(user == null){
                throw new CustomException(CodeEnum.USER_NOT_FOUND);
            }
        }
        if(!PasswordUtils.match(password,user.getPassword())){
            throw new CustomException(CodeEnum.PASSWORD_ERROR);
        }
        StpUtil.login(user.getId());
        redisUtil.set(RedisConstants.USER_TOKEN, String.valueOf(user.getId()),StpUtil.getTokenValue());
        redisUtil.set(RedisConstants.USER,String.valueOf(user.getId()), JSON.toJSONString(user));
        return StpUtil.getTokenValue();
    }

    @Override
    public User getUserByName(String username) {
        return lambdaQuery().select(User::getId,User::getUsername,User::getPassword,User::getType,User::getAvatar).eq(User::getUsername,username).one();
    }
    @Override
    public Boolean bindRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole().setUserId(userId).setRoleId(roleId);
        return userRoleService.save(userRole);
    }

    @Override
    public Boolean unbindRole(Long userId, Long roleId) {
        return userRoleService.unbind(userId,roleId);
    }

    @Override
    public User appendInfo(User user) {
        List<Long> roleIdsByUserId = userRoleService.getRoleIdsByUserId(user.getId());
        List<String> roleNamesByUserId = userRoleService.getRoleNamesByUserId(user.getId());
        return user.setRoleIds(roleIdsByUserId).setRoleNames(roleNamesByUserId);
    }

    /**
     * 通过电子邮件获取用户
     *
     * @param email 电子邮件
     * @return {@link User}
     */
    private User getUserByEmail(String email){
        return lambdaQuery().select(User::getId,User::getUsername,User::getPassword,User::getType)
                .eq(User::getEmail,email)
                .one();
    }
}




