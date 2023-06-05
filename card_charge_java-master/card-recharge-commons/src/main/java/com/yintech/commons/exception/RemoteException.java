package com.yintech.commons.exception;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 通过http等rpc调用其他系统造成的异常
 * @Company: yintech
 * @date 2021/6/21 4:46 下午
 */
public class RemoteException extends BaseException {
    public RemoteException() {
        super();
    }

    public RemoteException(String msg) {
        super(msg);
    }

    public RemoteException(String msg, Throwable e) {
        super(msg, e);
    }

    public RemoteException(Throwable cause) {
        super(cause);
    }
}
