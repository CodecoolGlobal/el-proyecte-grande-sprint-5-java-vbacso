package com.codecool.byteMe.config;

import com.codecool.byteMe.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;

@Configuration
@ComponentScan(basePackageClasses = User.class)
public class Config {

    @Bean
    public User info() throws IOException {
        return new User("Bob",20,"asd@asd.com",LocalDate.now());
    }

}
