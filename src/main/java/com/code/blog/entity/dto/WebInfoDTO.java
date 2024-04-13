package com.code.blog.entity.dto;


import com.code.blog.entity.WebInfo;
import com.code.blog.entity.base.ConverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Web信息
 *
 * @author HeXin
 * @date 2024/03/09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WebInfoDTO extends ConverEntity<WebInfo> {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 网站名称
     */
    @Schema(description = "网站名称")
    private String webName;

    /**
     * 网站信息
     */
    @Schema(description = "网站信息")
    private String webTitle;

    /**
     * 公告
     */
    @Schema(description = "公告")
    private String notices;

    /**
     * 页脚
     */
    @Schema(description = "页脚")
    private String footer;

    /**
     * 背景
     */
    @Schema(description = "背景")
    private String backgroundImage;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 随机头像
     */
    @Schema(description = "随机头像")
    private String randomAvatar;

    /**
     * 随机名称
     */
    @Schema(description = "随机名称")
    private String randomName;

    /**
     * 随机封面
     */
    @Schema(description = "随机封面")
    private String randomCover;

    /**
     * 是否启用[0:否，1:是]
     */
    @Schema(description = "是否启用[0:否，1:是]")
    private Integer status;
}
