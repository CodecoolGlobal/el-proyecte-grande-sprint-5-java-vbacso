package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.AppDao;
import com.codecool.byteMe.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("appService")
public class AppService {
    private final AppDao appDao;

    private AppService(AppDao appDao) {
        this.appDao = appDao;
    }

    public Set<User> getAllUser() {
        return appDao.getAllUser();
    }

    public User add(User user) {
        return appDao.addUser(user);
    }

    public User findByEmail(String email) {
        return appDao.findUserByEmail(email);
    }
}
