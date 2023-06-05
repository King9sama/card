package com.yintech.cloud.plugin;

import com.yintech.cloud.annotation.DbFieldEncrypt;
import com.yintech.commons.utils.EncryptUtils;
import com.yintech.commons.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * @version 1.0-SNAPSHOT
 * @program yintech-access-permit
 * @description 数据库字段解密mybatis插件
 * @author: ming.li1@yintech.cn
 * @createTime: 2022-08-31 17:34
 */

@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {java.sql.Statement.class})})
@Component
@Slf4j
public class DecryptInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (Objects.isNull(result)) {
            return null;
        }
        if (result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            resultList.stream().forEach(data -> decrypt(data));
        } else {
            decrypt(result);
        }
        return result;
    }

    private void decrypt(Object object) {
        try {
            Class<?> resultClass = object.getClass();
            Field[] declaredFields = resultClass.getDeclaredFields();
            if (Objects.isNull(declaredFields) || declaredFields.length == 0) {
                return;
            }
            for (Field field : declaredFields) {
                field.setAccessible(true);
                DbFieldEncrypt annotation = field.getAnnotation(DbFieldEncrypt.class);
                if (annotation != null && Objects.nonNull(field.get(object)) && annotation.select() && !StringUtil.isEmpty(annotation.aesKey())) {
                    log.debug("db字段解密,{}", field.getName());
                    String value = (String) field.get(object);
                    String decryptValue = EncryptUtils.decryptAes(annotation.aesKey(), value);
                    field.set(object, decryptValue);
                }
            }
        } catch (Exception e) {
            log.error("db数据解密异常,{}", e);
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
