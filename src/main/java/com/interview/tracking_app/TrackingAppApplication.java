package com.interview.tracking_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TrackingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackingAppApplication.class, args);
    }

}
