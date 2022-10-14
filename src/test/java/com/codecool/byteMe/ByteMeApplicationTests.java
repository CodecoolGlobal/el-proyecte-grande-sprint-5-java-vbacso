package com.codecool.byteMe;

import com.codecool.byteMe.config.DaoConfig;
import com.codecool.byteMe.config.PostConfig;
import com.codecool.byteMe.config.UserConfig;
import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class ByteMeApplicationTests {

    @Test
    void daoIsSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoConfig.class, UserConfig.class, PostConfig.class);

        UserService firstUserService = new UserService((UserDao) ac.getBean("userDao"));
        firstUserService.add(new User("Test User", 2, "kacsa@byte.me"));

        UserService secondUserService = new UserService((UserDao) ac.getBean("userDao"));

        Assertions.assertEquals(firstUserService.getAll(),secondUserService.getAll());
    }

}
