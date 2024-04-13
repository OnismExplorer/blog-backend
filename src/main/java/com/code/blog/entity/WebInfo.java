package com.code.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import com.code.blog.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 网站信息表
 * @author HeXin
 * @TableName web_info
 */
@TableName(value ="web_info")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class WebInfo extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 网站名称
     */
    @TableField(value = "web_name")
    @Schema(description = "网站名称")
    private String webName;

    /**
     * 网站信息
     */
    @TableField(value = "web_title")
    @Schema(description = "网站信息")
    private String webTitle;

    /**
     * 公告
     */
    @TableField(value = "notices")
    @Schema(description = "公告")
    private String notices;

    /**
     * 页脚
     */
    @TableField(value = "footer")
    @Schema(description = "页脚")
    private String footer;

    /**
     * 背景
     */
    @TableField(value = "background_image")
    @Schema(description = "背景")
    private String backgroundImage;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @Schema(description = "头像")
    private String avatar;

    /**
     * 随机头像
     */
    @TableField(value = "random_avatar")
    @Schema(description = "随机头像")
    private String randomAvatar;

    /**
     * 随机名称
     */
    @TableField(value = "random_name")
    @Schema(description = "随机名称")
    private String randomName;

    /**
     * 随机封面
     */
    @TableField(value = "random_cover")
    @Schema(description = "随机封面")
    private String randomCover;

    /**
     * 是否启用[0:否，1:是]
     */
    @TableField(value = "status")
    @Schema(description = "是否启用[0:否，1:是]")
    private Integer status;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}