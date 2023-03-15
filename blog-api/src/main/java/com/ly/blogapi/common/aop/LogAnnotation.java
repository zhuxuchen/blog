package com.ly.blogapi.common.aop;

import java.lang.annotation.*;

/**
 * <p>
 *     日志记录
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-15
 */
//ElementType.TYPE代表可以放在类上面  method代表可以放在方法上
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";

    String operation() default "";
}
