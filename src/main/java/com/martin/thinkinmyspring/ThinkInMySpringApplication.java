package com.martin.thinkinmyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableScheduling
public class ThinkInMySpringApplication {

    public static void main(String[] args) {

        SpringApplication.run(ThinkInMySpringApplication.class, args);
        System.out.println("==========启动成功======================");
    }

//    @Primary
//    @Bean
//    public TaskExecutor primaryTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        // add necessary properties to the executor
//        return executor;
//    }

}
