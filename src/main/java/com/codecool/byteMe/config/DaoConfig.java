package com.codecool.byteMe.config;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.dao.mem.UserDaoMem;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Set;

@Configuration
public class DaoConfig {

    UserConfig userConfig;

    @Autowired
    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    @Bean
    @Scope("singleton")
    UserDao userDao() {
        return new UserDaoMem();
    }
}
