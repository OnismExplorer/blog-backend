package com.code.blog.constants;

import org.springframework.util.StringUtils;

/**
 * 评论类型常量
 *
 * @author HeXin
 * @date 2024/03/10
 */
public enum CommentTypeEnum {
    COMMENT_TYPE_ARTICLE("article", "文章评论"),
    COMMENT_TYPE_MESSAGE("treehole", "树洞留言"),
    COMMENT_TYPE_LOVE("weiyan", "微言留言");

    private final String type;
    private final String description;

    CommentTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static CommentTypeEnum getEnumByCode(String code) {
        if (StringUtils.hasText(code)) {
            CommentTypeEnum[] values = CommentTypeEnum.values();
            for (CommentTypeEnum typeEnum : values) {
                if (typeEnum.getType().equalsIgnoreCase(code)) {
                    return typeEnum;
                }
            }
        }
        return null;
    }
}
