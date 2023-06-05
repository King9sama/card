package com.yintech.commons.exception;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 传参异常，参数不符合要求
 * @Company: yintech
 * @date 2021/6/21 4:42 下午
 */
public class ParamException extends BaseException {
    public ParamException() {
        super();
    }

    public ParamException(String msg) {
        super(msg);
    }

    public ParamException(String msg, Throwable e) {
        super(msg, e);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }
}
