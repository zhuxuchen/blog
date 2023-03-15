package com.ly.blogapi.common.cache;

import java.lang.annotation.*;

/**
 * <p>
 *     统一缓存
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-15
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    //过期时间
    long expire() default 1 * 60 * 1000;
    //缓存标识 key
    String name() default "";
}
