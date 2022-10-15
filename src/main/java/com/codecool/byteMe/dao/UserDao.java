package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;

import java.util.Set;
import java.util.UUID;

public interface UserDao {
    Set<User> getAll();

    User add(User user);

    User edit(User user);

    User findByEmail(String email);

    User findById(UUID userId);

    User delete(UUID userId);
}
