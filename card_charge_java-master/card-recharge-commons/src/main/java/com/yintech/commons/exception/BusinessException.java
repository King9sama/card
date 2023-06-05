package com.yintech.commons.exception;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 特定业务场景下产生的异常
 * @Company: yintech
 * @date 2021/6/21 4:54 下午
 */
public class BusinessException extends BaseException {
    public BusinessException() {
        super();
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
