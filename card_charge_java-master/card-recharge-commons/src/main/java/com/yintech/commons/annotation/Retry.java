package com.yintech.commons.annotation;

import java.lang.annotation.*;

/**
 * 方法重试 默认方法抛出异常重试
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retry {
    /**
     * 方法备注，可以写方法的功能
     *
     * @return
     */
    String remark() default "";

    /**
     * 重试次数，默认3次
     *
     * @return
     */
    int retryNum() default 3;

    /**
     * 重试间隔，默认立即重试，单位毫秒
     *
     * @return
     */
    long duration() default 0;
}
