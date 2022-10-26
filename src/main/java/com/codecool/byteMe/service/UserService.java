package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.dao.mem.UserDaoMem;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoMem();
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
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

    public User findByIdAdd(UUID userId, Post post) {
        return userDao.findByIdAdd(userId, post);
    }
}
