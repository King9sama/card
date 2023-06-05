package com.yintech.cloud.plugin;

import com.yintech.cloud.annotation.DbFieldEncrypt;
import com.yintech.commons.utils.EncryptUtils;
import com.yintech.commons.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

/**
 * @version 1.0-SNAPSHOT
 * @program yintech-access-permit
 * @description 数据库字段加密mybatis插件
 * @author: ming.li1@yintech.cn
 * @createTime: 2022-08-31 17:34
 */
@Component
@Slf4j
@Intercepts({@Signature(method = "setParameters", type = ParameterHandler.class, args = PreparedStatement.class)})
public class EncryptInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof ParameterHandler) {
            //获取拦截对象
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
            if (Objects.nonNull(parameterField)) {
                parameterField.setAccessible(true);
                Object parameterObject = parameterField.get(parameterHandler);
                if (Objects.nonNull(parameterObject)) {
                    if (parameterObject instanceof HashMap) {
                        HashMap parameterObjectMap = (HashMap) parameterObject;
                        if (parameterObjectMap.containsKey("record")) {
                            Object record = parameterObjectMap.get("record");
                            if (record instanceof ArrayList) {
                                for (Object o : (ArrayList) record) {
                                    encryptField(o);
                                }
                            } else {
                                encryptField(record);
                            }
                        }
                        if (parameterObjectMap.containsKey("list")) {
                            Object list = parameterObjectMap.get("list");
                            if (list instanceof ArrayList) {
                                for (Object o : (ArrayList) list) {
                                    encryptField(o);
                                }
                            } else {
                                encryptField(list);
                            }
                        }
                    } else {
                        encryptField(parameterObject);
                    }
                }
            }
        }
        return invocation.proceed();
    }

    /**
     * 字段加密
     *
     * @param object
     * @throws IllegalAccessException
     */
    private void encryptField(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            DbFieldEncrypt annotation = field.getAnnotation(DbFieldEncrypt.class);
            if (annotation != null && annotation.insetOrUpdate() && !StringUtil.isEmpty(annotation.aesKey()) && Objects.nonNull(field.get(object))) {
                log.debug("db字段加密,{}", field.getName());
                String value = (String) field.get(object);
                String encryptValue = EncryptUtils.encryptAes(annotation.aesKey(), value);
                field.set(object, encryptValue);
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}