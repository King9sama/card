package com.yintech.commons.exception;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 未授权/无权限
 * @Company: yintech
 * @date 2021/6/21 4:56 下午
 */
public class NoPermissionException extends BaseException {
    public NoPermissionException() {
        super();
    }

    public NoPermissionException(String msg) {
        super(msg);
    }

    public NoPermissionException(String msg, Throwable e) {
        super(msg, e);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }
}
