package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public interface PostDao {
    Set<Post> getAllPost();
    Post findById(UUID postId);

    Set<Post> findPostsByUserId(UUID userId);

    Post addPost(Post post);

    Post editPost(Post post);

    Post deletePost(UUID postId);

}
