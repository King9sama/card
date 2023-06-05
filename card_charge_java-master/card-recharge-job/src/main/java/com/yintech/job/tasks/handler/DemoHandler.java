package com.yintech.job.tasks.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description
 * @Company: yintech
 * @date 2021/6/29 1:47 下午
 */
@Component
@Slf4j
public class DemoHandler {
    public void doTask() {
        log.info("do task ...");
    }
}
