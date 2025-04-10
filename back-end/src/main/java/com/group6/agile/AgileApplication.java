package com.group6.agile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AgileApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgileApplication.class, args);
    }

}
