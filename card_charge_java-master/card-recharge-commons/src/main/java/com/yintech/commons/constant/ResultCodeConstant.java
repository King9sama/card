package com.yintech.commons.constant;


/**
 * 系统统一返回结果
 */
public enum ResultCodeConstant   {


    SUCCESS(0, "SUCCESS"),

    ERROR(-1, "系统异常");

    private int code;

    private String msg;


    ResultCodeConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
