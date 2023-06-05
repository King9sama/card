package com.yintech.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description Json工具类，使用fastJson
 * @Company: yintech
 * @date 2021/6/21 4:34 下午
 */

@Slf4j
public class JsonUtil {
    private static final SerializerFeature[] FEATURES = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty
    };
    private static SerializeConfig config;

    static {
        config = new SerializeConfig();
        SimpleDateFormatSerializer df = new SimpleDateFormatSerializer(
                "yyyy-MM-dd HH:mm:ss");
        config.put(java.util.Date.class, df);
        config.put(java.sql.Date.class, df);
        config.put(Timestamp.class, df);
    }

    /***
     * 序列化数据
     *
     * @param object
     * @return
     */
    public static String toJSON(Object object) {
        return JSON.toJSONString(object, FEATURES);
    }

    public static byte[] toJsonBytes(Object object) {
        return JSON.toJSONBytes(object, FEATURES);
    }

    /***
     * 反序列化数据
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parse(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /***
     * 反序列化携带泛型的数据
     *
     * @param json
     * @param type
     * @return
     */
    public static <T> T parse(String json, Type type) {
        return JSON.parseObject(json, type);
    }


    /***
     * 反序列化数据
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseNotThrowException(String json, Class<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return t;
    }

    /***
     * 反序列化数据
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /***
     * 反序列化数据
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArrayNotThrowException(String json, Class<T> clazz) {
        List<T> list = null;
        try {
            list = JSON.parseArray(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;
    }
}
