package com.pangapiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PangApiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PangApiServerApplication.class, args);
    }
}