package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.dao.mem.PostDaoMem;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service("postService")
public class PostService {
    private PostDao postDao;

    public PostService() {
        this.postDao = new PostDaoMem();
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public Set<Post> getAll() {
        return postDao.getAll();
    }

    public Post add(Post post) {
        return postDao.add(post);
    }

    public Post findById(UUID postId) {
        return postDao.findById(postId);
    }

    public Set<Post> findByUserId(UUID userId) {
        return postDao.findByUserId(userId);
    }

    public Post edit(Post post) {
        return postDao.edit(post);
    }

    public Post delete(UUID postId) {
        return postDao.delete(postId);
    }

    public Set<Post> getAllFriendsPosts(UUID userId) {
        return postDao.getAllFriendsPosts(userId);
    }
}
