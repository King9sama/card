package com.yintech.commons.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.yintech.commons.constant.SignConstant;
import com.yintech.commons.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @program: oa-center
 * @description: 加密工具
 * @author:ming.li1@yintech.cn
 * @create: 2021-01-22 14:29
 */
@Slf4j
public class EncryptUtils {

    public static final String AES_KEY = "f/bW0Z/0Csud2WRJZ6lqGA==";

    /**
     * md5加密
     *
     * @param body
     * @return
     */
    public static String md5(String body) {
        return SecureUtil.md5(body);
    }

    /**
     * base64加密
     *
     * @param body
     * @return
     */
    public static String base64(String body) {
        return Base64.encode(body);
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param md5  密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String md5) {
        String md5Str = md5(text);
        if (md5Str.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }

    /**
     * 校验2个md5
     *
     * @param newMd5
     * @param md5
     * @return
     */
    public static boolean verifyMd5(String newMd5, String md5) {
        if (StringUtil.isEmpty(newMd5) || StringUtil.isEmpty(md5)) {
            return false;
        }
        return newMd5.equalsIgnoreCase(md5);
    }

    /**
     * 加密返回HttpRequest
     *
     * @param jsonParamMap
     * @param key
     * @return
     */
    public static HttpRequest encryptJsonDataForHutHttp(HttpRequest httpRequest, Map<String, Object> jsonParamMap, String appId, String key) {
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        if (Objects.isNull(httpRequest)) {
            throw new ValidateException("httpRequest不能为空");
        }
        String timestamp = System.currentTimeMillis() + "";
        String sign = md5(key + JSON.toJSONString(jsonParamMap) + timestamp);
        httpRequest.header(SignConstant.SIGN, sign)
                .header(SignConstant.APP_ID, appId)
                .header(SignConstant.TIMESTAMP, timestamp)
                .body(JSON.toJSONString(jsonParamMap));
        return httpRequest;
    }


    /**
     * 加密返回map
     *
     * @param jsonParamMap
     * @param appId
     * @param key
     * @return
     */
    public static Map<String, String> encryptJsonDataToMap(Map<String, Object> jsonParamMap, String appId, String key) {
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        String timestamp = System.currentTimeMillis() + "";
        String sign = md5(key + JSON.toJSONString(jsonParamMap) + timestamp);
        Map<String, String> signMap = new HashMap<>();
        signMap.put(SignConstant.SIGN, sign);
        signMap.put(SignConstant.APP_ID, appId);
        signMap.put(SignConstant.TIMESTAMP, timestamp);
        return signMap;
    }

    /**
     * 加密返回签名
     *
     * @param jsonParamMap
     * @param key
     * @param timestamp
     * @return
     */
    public static String encryptJsonData(Map<String, Object> jsonParamMap, String key, String timestamp) {
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        if (Objects.isNull(timestamp)) {
            throw new ValidateException("时间戳不能为空");
        }
        String sign = md5(key + JSON.toJSONString(jsonParamMap) + timestamp);
        return sign;
    }

    /**
     * 加密表单请求数据，返回HttpRequest
     *
     * @param httpRequest
     * @param formData
     * @param appId
     * @param key
     * @return
     */
    public static HttpRequest encryptFormDataForHutHttp(HttpRequest httpRequest, Map<String, Object> formData, String appId, String key) {
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        if (Objects.isNull(httpRequest)) {
            throw new ValidateException("httpRequest不能为空");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        if (Objects.nonNull(formData)) {
            List<Map.Entry<String, Object>> requestParam = new ArrayList<>(formData.entrySet());
            requestParam.sort((o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey()));
            for (Map.Entry<String, Object> entry : requestParam) {
                sb.append(entry.getValue());
            }
        }
        String timestamp = System.currentTimeMillis() + "";
        sb.append(timestamp);
        String sign = md5(sb.toString());
        httpRequest.header("sign", sign)
                .header("appId", appId)
                .header("timestamp", timestamp)
                .form(formData);
        return httpRequest;
    }

    /**
     * 加密表单请求参数，返回MAP
     *
     * @param formData
     * @param appId
     * @param key
     * @return
     */
    public static Map<String, String> encryptFormDataToMap(Map<String, Object> formData, String appId, String key) {
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        if (Objects.nonNull(formData)) {
            List<Map.Entry<String, Object>> requestParam = new ArrayList<>(formData.entrySet());
            requestParam.sort((o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey()));
            for (Map.Entry<String, Object> entry : requestParam) {
                sb.append(entry.getValue());
            }
        }
        String timestamp = System.currentTimeMillis() + "";
        sb.append(timestamp);
        Map<String, String> signMap = new HashMap<>();
        signMap.put(SignConstant.SIGN, md5(sb.toString()));
        signMap.put(SignConstant.APP_ID, appId);
        signMap.put(SignConstant.TIMESTAMP, timestamp);
        return signMap;
    }

    /**
     * 获取表单请求签名
     *
     * @param formData
     * @param key
     * @param timestamp
     * @return
     */
    public static String encryptFormData(Map<String, Object> formData, String key, String timestamp) {
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        if (Objects.nonNull(formData)) {
            List<Map.Entry<String, Object>> requestParam = new ArrayList<>(formData.entrySet());
            requestParam.sort((o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey()));
            for (Map.Entry<String, Object> entry : requestParam) {
                sb.append(entry.getValue());
            }
        }
        sb.append(timestamp);
        return md5(sb.toString());
    }

    /**
     * aes堆成加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encryptAes(String key, String data) {
        if (StringUtil.isEmpty(data)) {
            return data;
        }
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        try {
            byte[] decode = Base64.decode(key);
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, decode);
            return aes.encryptHex(data);
        } catch (Exception e) {
            log.error("加密异常,{}", e);
            return data;
        }
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decryptAes(String key, String data) {
        if (StringUtil.isEmpty(data)) {
            return data;
        }
        if (StringUtil.isEmpty(key)) {
            throw new ValidateException("加密key不能为空");
        }
        try {
            byte[] decode = Base64.decode(key);
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, decode);
            return aes.decryptStr(data);
        } catch (Exception e) {
            log.error("解密异常,{}", e);
            return data;
        }
    }

    /**
     * 生成Aes加密key
     *
     * @return
     */
    public static String createAesKey() {
        String key = UUID.randomUUID().toString().replace("-", "");
        return Base64.encode(key.getBytes());
    }

    /**
     * 脱敏手机号
     *
     * @param mobile
     * @return 脱敏后字符串
     */
    public static String maskPhone(String mobile) {
        if (StringUtil.isEmpty(mobile)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\w{4})", "$1****$2");
    }


    /**
     * 身份证号脱敏
     *
     * @param card
     * @return
     */
    public static String maskCard(String card) {
        if (StringUtil.isEmpty(card)) {
            return card;
        }
        int encLength = card.length() - 8;
        if (encLength < 0) {
            encLength = 0;
        }
        String regex = "(\\d{4})\\d{" + encLength + "}(\\w{4})";
        return card.replaceAll(regex, "$1**********$2");
    }
}
