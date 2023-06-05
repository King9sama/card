package com.yintech.commons.constant;

/**
 * @Description
 * @Author jiahao.liu
 * @Data 2023/5/22 10:09
 */
public enum ResponseCodeEnum {

    //编写枚举的值，调用构造方法，使用逗号隔开
    SUCCESS("200", "SUCCESS"),
    /*
    201 校验异常等描述:比如验签失败等
    203 传入参数验证异常，具体看msg返回内容
    19003 验证身份信息错误
    CE6018 数据库操作异常
    CARD0002 只有已售卡才能圭失
    CE6016 新卡不存在
    CED0013 未售卡
    CEDO014 卡已挂失
    CED0015 已冻结
    CED0016 卡已换卡
    CEDO017 卡已退卡
    19004 传入的卡号非用户的卡号
    16001 数据未找到
    16002 处理超时
    16003 未成功加载配置文件(IO异常)
    16004 连接数据库超时
    16005 Sql异常
    * */

    null_input("160100","接口输入参数为空串或者null"),
    illegal_input("160101","接口输入参数不合法"),
    illegal_sign("160102", "报文校验失败(sign不合法)");
    //变量
    private String code;
    private String description;

    //枚举类构造方法
    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;

    }
}
