package com.yintech.commons.constant;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 脱敏枚举信息
 * @Company: yintech
 * @date 2021/6/24 1:49 下午
 */
public enum DesensitizeEnum {
    /**
     * 手机号
     */
    MOBILE("手机号"),
    /**
     * 身份证号
     */
    CARD("身份证号");

    private String type;

    DesensitizeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
