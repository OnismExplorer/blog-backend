package com.code.blog.utils.cache;


import com.code.blog.constants.RedisConstants;
import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 缓存注解
 * @author HeXin
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * 键
     * @return {@link String}
     */
    String key() default "";

    /**
     * 时间
     *
     * @return long
     */
    long time() default 10L;

    /**
     * 时间单位
     *
     * @return {@link TimeUnit}
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 常量
     * 常数
     *
     * @return {@link RedisConstants}
     */
    RedisConstants constants() default RedisConstants.DEFAULT;
}
