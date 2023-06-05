package com.yintech.commons.exception;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 系统过载，超出限流造成的异常
 * @Company: yintech
 * @date 2021/6/21 4:58 下午
 */
public class OverLimitException extends BaseException {
    public OverLimitException() {
        super();
    }

    public OverLimitException(String msg) {
        super(msg);
    }

    public OverLimitException(String msg, Throwable e) {
        super(msg, e);
    }

    public OverLimitException(Throwable cause) {
        super(cause);
    }
}
