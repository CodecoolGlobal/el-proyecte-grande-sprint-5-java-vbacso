package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public interface PostDao {
    Set<Post> getAll();

    Post findById(UUID postId);

    Set<Post> findByUserId(UUID userId);

    Post add(Post post);

    Post edit(Post post);

    Post delete(UUID postId);

}
