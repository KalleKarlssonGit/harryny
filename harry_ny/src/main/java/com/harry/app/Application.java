package com.harry.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.harry.app", "com.harry.controller", "com.harry.domain", "com.harry.exception", "com.harry.generated", "com.harry.service"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
