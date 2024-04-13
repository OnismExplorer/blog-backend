package com.code.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户信息
 *
 * @author HeXin
 * @date 2024/03/08
 */
@Data
@Accessors(chain = true)
public class UserInfo {
    @Schema(description = "用户id")
    private String id;
    @Schema(description = "用户角色")
    private List<String> roleNames;
    @Schema(description = "用户头像")
    private String avatar;
    @Schema(description = "用户名称")
    private String name;
}
