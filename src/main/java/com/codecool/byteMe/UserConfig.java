package com.codecool.byteMe;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@Configuration
@ComponentScan(basePackageClasses = UserDao.class)
public class UserConfig {
    @Bean
    public User getVanda(){
        return new User("Vanda", 18, LocalDate.now());
    }
}
