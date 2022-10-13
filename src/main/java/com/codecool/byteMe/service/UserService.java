package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("userService")
public class UserService {
    private final UserDao userDao;

    private UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Set<User> getAllUser() {
        return userDao.getAllUser();
    }

    public User add(User user) {
        return userDao.add(user);
    }
}
