package com.yintech.commons.exception;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 内部参数校验异常
 * @Company: yintech
 * @date 2021/6/21 4:51 下午
 */
public class ValidateException extends BaseException {
    public ValidateException() {
        super();
    }

    public ValidateException(String msg) {
        super(msg);
    }

    public ValidateException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }
}
