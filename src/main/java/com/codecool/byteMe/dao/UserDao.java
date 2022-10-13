package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public interface UserDao {
    User add(User user);

    User findByEmail(String email);

    Set<Post> findByUser(UUID userId);

    Set<User> getAllUser();
}
