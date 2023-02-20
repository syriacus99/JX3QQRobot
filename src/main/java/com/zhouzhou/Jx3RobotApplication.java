package com.zhouzhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.zhouzhou.feignClient")
public class Jx3RobotApplication {

    public static void main(String[] args) {
        String a = "a ";
        String[] split = a.split(" ");
        System.out.println(split.length);
        SpringApplication.run(Jx3RobotApplication.class, args);
    }

}
