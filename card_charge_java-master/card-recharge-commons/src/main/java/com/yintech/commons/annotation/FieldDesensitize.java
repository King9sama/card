package com.yintech.commons.annotation;

import com.yintech.commons.constant.DesensitizeEnum;

import java.lang.annotation.*;

/**
 * 基础信息脱敏
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldDesensitize {
    DesensitizeEnum type();
}
