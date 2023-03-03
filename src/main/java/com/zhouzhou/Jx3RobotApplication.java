package com.zhouzhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.zhouzhou.feignClient")
public class Jx3RobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jx3RobotApplication.class, args);
    }

}
