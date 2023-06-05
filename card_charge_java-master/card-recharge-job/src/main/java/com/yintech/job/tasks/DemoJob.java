package com.yintech.job.tasks;

import com.xxl.job.core.handler.annotation.XxlJob;
import com.yintech.job.tasks.handler.DemoHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description
 * @Company: yintech
 * @date 2021/6/29 1:31 下午
 */
@Component
@Slf4j
public class DemoJob {
    @Autowired
    DemoHandler handler;

    @XxlJob(value = "demoJob")
    public void demoJob() {
        handler.doTask();
        ;
    }
}
