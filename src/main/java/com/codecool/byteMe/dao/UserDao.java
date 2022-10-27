package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public interface UserDao {
    Set<User> getAll();

    User add(User user);

    User edit(User user);

    User findByEmail(String email);

    User findById(UUID userId);

    User delete(UUID userId);

    User findByIdAdd(UUID userId, Post post);

    User findByIdDelete(UUID userId, UUID postId);
}
