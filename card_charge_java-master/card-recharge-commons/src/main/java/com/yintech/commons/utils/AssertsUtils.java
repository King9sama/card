package com.yintech.commons.utils;

import com.yintech.commons.exception.BusinessException;

/**
 * @program: card-recharge
 * @description:断言抛出异常
 * @author:ming.li1@yintech.cn
 * @create: 2021-12-15 14:48
 */
public class AssertsUtils {
    /**
     * @param message
     */
    public static void fail(String message) {
        throw new BusinessException(message);
    }
}
