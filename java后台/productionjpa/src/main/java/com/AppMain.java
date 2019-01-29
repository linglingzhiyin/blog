package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class AppMain {
    private static final Logger logger = LoggerFactory.getLogger(AppMain.class);
    public static void main(String[] agrs){
        SpringApplication.run(AppMain.class);
        logger.info("========================启动完毕========================");
    }

}
