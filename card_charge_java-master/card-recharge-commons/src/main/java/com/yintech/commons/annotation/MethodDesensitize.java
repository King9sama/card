package com.yintech.commons.annotation;

import java.lang.annotation.*;

/**
 * 方法返回脱敏标志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodDesensitize {
}
