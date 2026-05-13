package com.example.demoqr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DynamicQrApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicQrApplication.class, args);
    }

}
