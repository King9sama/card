package com.yintech.job.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description xxl-job配置
 * @Company: yintech
 * @date 2021/6/29 1:33 下午
 */
@Configuration
@Slf4j
public class XxlJobConfig {
    /**
     * xxl-admin地址
     */
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken}")
    private String accessToken;
    /**
     * xxl-admin上执行器名称
     */
    @Value("${xxl.job.executor.appname}")
    private String appname;
    /**
     * 执行器地址
     */
    @Value("${xxl.job.executor.address}")
    private String address;
    /**
     * 执行器的ip
     */
    @Value("${xxl.job.executor.ip}")
    private String ip;
    /**
     * 和xxl-admin通信端口
     */
    @Value("${xxl.job.executor.port}")
    private int port;
    /**
     * xxl-job日志路径
     */
    @Value("${xxl.job.executor.logpath}")
    private String logPath;
    /**
     * 日志保留时间
     */
    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }
}
