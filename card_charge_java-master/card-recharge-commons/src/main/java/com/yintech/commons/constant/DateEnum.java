package com.yintech.commons.constant;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description
 * @Company: yintech
 * @date 2021/6/24 1:49 下午
 */
public enum DateEnum {
    /**
     * 年
     */
    YEAR("年"),
    /**
     * 月
     */
    MONTH("月"),
    /**
     * 周
     */
    WEEK("周"),
    /**
     * 日
     */
    DAY("日"),
    /**
     * 时
     */
    HOUR("时"),
    /**
     * 分
     */
    MINUTE("分"),
    /**
     * 秒
     */
    SECOND("秒");

    private String type;

    DateEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
