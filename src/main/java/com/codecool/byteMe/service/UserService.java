package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("userService")
public class UserService {
    @Autowired
    UserDao userDaoMem;
    
    public Set<User> getAllUser() {
        return userDaoMem.getAllUser();
    }

    public User add(User user) {
        return userDaoMem.add(user);
    }
}
