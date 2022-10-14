package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service("postService")
public class PostService {
    private PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public Set<Post> getAllPost() {
        return postDao.getAllPost();
    }

    public Post addPost(Post post) {
        return postDao.addPost(post);
    }

    public Post editPost(Post post) {
        return postDao.editPost(post);
    }

    public Set<Post> findPostsByUserId(UUID userId) {
        return postDao.findPostsByUserId(userId);
    }

    public Post findPostById(UUID postId) {
        return postDao.findById(postId);
    }

    public Post deletePost(UUID postId) {
        return postDao.deletePost(postId);
    }
}
