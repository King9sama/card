package com.yintech.commons.utils;

import cn.hutool.http.HttpRequest;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description http工具，封装hutool http工具
 * @Company: yintech
 * @date 2021/6/24 1:35 下午
 */
public class HttpUtils extends HttpRequest {
    public HttpUtils(String url) {
        super(url);
    }
}
