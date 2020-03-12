package com.martin.thinkinmyspring.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 获取cw统计数据定时任务
 * @program: store
 * @description:
 * @author: martin
 * @create: 2020-03-11 10:18
 * @Version 0.1
 **/
@Component
public class CwHttpDataJob {
    private static final Logger logger = LoggerFactory.getLogger(CwHttpDataJob.class);


    @Scheduled(cron="0/5 * * * * ?")
    private void testTasks() {
        System.out.println("==========定时任务开始执行======================");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("静态任务"+now);
    }

}
