package com.yintech.job.config.aspect;

import com.xxl.job.core.handler.annotation.XxlJob;
import com.yintech.commons.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 日志添加流水号&记录每个任务执行耗时情况
 * @Company: yintech
 * @date 2021/6/29 1:31 下午
 */
@Component
@Aspect
@Slf4j
public class JobLogAspect {

    @Around("@annotation(com.xxl.job.core.handler.annotation.XxlJob))")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        LogUtils.insert();
        long start = System.currentTimeMillis();
        try {
            MethodSignature ms = (MethodSignature) point.getSignature();
            Method method = ms.getMethod();
            XxlJob annotation = method.getAnnotation(XxlJob.class);
            String jobName = Objects.isNull(annotation) ? "DefaultJob" : annotation.value();
            log.info("[{}]->>>>>>>>>>xxl-job start", jobName);
            Object result = point.proceed();
            log.info("[{}]->>>>>>>>>>xxl-job end ,use {} ms", jobName, (System.currentTimeMillis() - start));
            return result;
        } finally {
            LogUtils.remove();
        }
    }
}
