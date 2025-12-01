package com.example.ridesharingdriversystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.ridesharingdriversystem.client")
public class RideSharingDriverSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideSharingDriverSystemApplication.class, args);
    }

}
