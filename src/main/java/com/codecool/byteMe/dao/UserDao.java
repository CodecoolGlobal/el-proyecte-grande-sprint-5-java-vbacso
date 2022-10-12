package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;

import java.util.Set;

public interface UserDao {
    void add(User user);

    User find(String email);

    Set<User> getAllUser();
}
