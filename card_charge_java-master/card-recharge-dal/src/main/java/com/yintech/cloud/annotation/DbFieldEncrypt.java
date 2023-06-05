package com.yintech.cloud.annotation;

import java.lang.annotation.*;

/**
 * 数据加密的标志
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbFieldEncrypt {
    /**
     * aes加密key
     *
     * @return
     */
    String aesKey() default "f/bW0Z/0Csud2WRJZ6lqGA==";

    /**
     * 新增或者更新是否加密，默认是
     *
     * @return
     */
    boolean insetOrUpdate() default true;

    /**
     * 查询是否解密，默认未true
     *
     * @return
     */
    boolean select() default true;
}
