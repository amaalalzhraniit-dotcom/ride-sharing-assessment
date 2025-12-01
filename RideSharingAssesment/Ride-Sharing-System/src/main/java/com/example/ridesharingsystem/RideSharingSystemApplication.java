package com.example.ridesharingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.ridesharingsystem.clients")
public class RideSharingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideSharingSystemApplication.class, args);
    }

}
