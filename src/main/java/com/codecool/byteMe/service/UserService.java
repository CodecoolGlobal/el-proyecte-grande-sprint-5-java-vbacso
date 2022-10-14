package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Set<User> getAll() {
        return userDao.getAll();
    }

    public User add(User user) {
        return userDao.add(user);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    public User findById(UUID uuid) {
        return userDao.findById(uuid);
    }

    public User edit(User user) {
        return userDao.edit(user);
    }
}
