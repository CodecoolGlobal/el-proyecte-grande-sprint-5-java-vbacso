package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public interface PostDao {
    Post add(Post post);

    Set<Post> findByUser(UUID userId);

    Post editPost(Post post);

    void deletePost(UUID postId);
}
