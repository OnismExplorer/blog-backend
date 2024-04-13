package com.code.blog.entity.dto;


import com.code.blog.entity.Role;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色dto
 *
 * @author HeXin
 * @date 2024/03/08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RoleDTO extends ConverEntity<Role> {
    private Long id;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色关键词(权限认证字段)")
    private String roleKey;

}
