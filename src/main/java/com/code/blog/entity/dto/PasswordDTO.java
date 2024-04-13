package com.code.blog.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 密码dto
 *
 * @author HeXin
 * @date 2024/03/08
 */
public record PasswordDTO(@Schema(description = "旧密码") String oldPassword, @Schema(description = "新密码") String newPassword, @Schema(description = "二次重复新密码") String confirmPassword) {
}
