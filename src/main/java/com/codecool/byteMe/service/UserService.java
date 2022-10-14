package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Set<User> getAllUser() {
        return userDao.getAllUser();
    }

    public User add(User user) {
        return userDao.addUser(user);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    public User findById(UUID uuid) {
        return userDao.findUserById(uuid);
    }

    public User edit(User user) {
        return userDao.edit(user);
    }
}
