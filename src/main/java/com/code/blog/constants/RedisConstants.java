package com.code.blog.constants;

import java.util.concurrent.TimeUnit;

/**
 * redis常量
 * @author HeXin
 */

public enum RedisConstants {

    /**
     * 默认
     */
    DEFAULT("",10L,TimeUnit.SECONDS),
    /**
     * 用户常量
     */
    USER("User:", 1800L, TimeUnit.SECONDS),
    /**
     * 用户角色常量
     */
    USER_ROLE("User:role:", 1800L, TimeUnit.SECONDS),
    /**
     * 点赞常量
     */
    LIKE("Like:", 600L, TimeUnit.SECONDS),
    /**
     * 用户权限常量
     */
    USER_PERMISSION("User:permission:", 1800L, TimeUnit.SECONDS),

    /**
      * 活动即将开始
      */
    ACTIVITY_UPCOMING("Activity:upcoming", 86400L, TimeUnit.SECONDS),
    /**
      * 活动即将结束
      */
    ACTIVITY_ENDING("Activity:ending", 86400L, TimeUnit.SECONDS),
    /**
     * 邮箱验证码常量
     */
    EMAIL_CODE("Email:code:", 300L, TimeUnit.SECONDS),

    /**
     * 用户令牌
     */
    USER_TOKEN("User:token:", -1L,TimeUnit.SECONDS ),
    /**
     * 评论计数
     */
    COMMENT_COUNT("Comment:count:", 1800L,TimeUnit.SECONDS),
    /**
     * 网站信息
     */
    WEB_INFO("Web:info:", 1800L,TimeUnit.SECONDS);
    /**
     * 键
     */
    private final String key;
    /**
     * 过期时间
     */
    private final Long ttl;

    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;

    RedisConstants(String key, Long ttl, TimeUnit timeUnit) {
        this.key = key;
        this.ttl = ttl;
        this.timeUnit = timeUnit;
    }

    public String getKey() {
        return key;
    }

    public Long getTtl() {
        return ttl;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
