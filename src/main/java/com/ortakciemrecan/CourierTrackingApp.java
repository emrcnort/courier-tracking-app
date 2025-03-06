package com.ortakciemrecan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
@ComponentScan(basePackages = {
        "com.ortakciemrecan",
})
public class CourierTrackingApp {
    public static void main(String[] args) {
        SpringApplication.run(CourierTrackingApp.class, args);
    }
}