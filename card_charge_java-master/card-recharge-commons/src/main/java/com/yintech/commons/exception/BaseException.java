package com.yintech.commons.exception;

/**
 * 异常基类
 *
 * @auth ming.li1
 */
public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable e) {
        super(msg, e);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
