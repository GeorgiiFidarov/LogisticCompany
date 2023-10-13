package ru.fidarov.infoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InfoManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfoManagementApplication.class, args);
    }
}