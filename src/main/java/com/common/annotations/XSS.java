package com.common.annotations;

import java.lang.annotation.*;

/**
 * @author super
 * Create time 2017/12/9 23:04
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XSS {

    String value() default "";

    String name() default "";
}
