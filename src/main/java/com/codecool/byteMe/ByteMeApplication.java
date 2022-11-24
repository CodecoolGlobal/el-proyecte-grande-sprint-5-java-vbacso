package com.codecool.byteMe;

import com.codecool.byteMe.security.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration.class)
public class ByteMeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ByteMeApplication.class, args);
    }
}
