package com.codecool.byteMe.config;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.dao.mem.UserDaoMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
        UserDao userDao = new UserDaoMem();
        userDao.add(userConfig.zeno());
        userDao.add(userConfig.vanda());
        userDao.add(userConfig.erik());
        userDao.add(userConfig.dani());
        return userDao;
    }
}
