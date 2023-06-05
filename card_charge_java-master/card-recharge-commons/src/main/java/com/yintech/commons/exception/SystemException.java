package com.yintech.commons.exception;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 系统内部处理异常
 * @Company: yintech
 * @date 2021/6/21 4:47 下午
 */
public class SystemException extends BaseException {
    public SystemException() {
        super();
    }

    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(String msg, Throwable e) {
        super(msg, e);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }
}
