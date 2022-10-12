package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;

public interface UserDao {
    User add(User user);

    User find(String email);
}
