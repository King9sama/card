package com.yintech.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description
 * @Company: yintech
 * @date 2021/6/29 1:48 下午
 */

@SpringBootApplication
@Slf4j
public class JobStarter {
    public static void main(String[] args) {
        SpringApplication.run(JobStarter.class, args);
        log.info("job is running ...");
    }

}