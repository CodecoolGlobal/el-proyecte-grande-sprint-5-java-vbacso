package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PostDaoMem implements PostDao {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Set<Post> getAll() {
        return userDao.getAll().stream()
                .map(User::getPosts)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Post add(Post post) {
        userDao.findById(post.getUserId()).addPost(post);
        return post;
    }

    @Override
    public Post findById(UUID postId) {
        return getAll().stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Post edit(Post post) {
        return findById(post.getId()).editPost(post);
    }


    @Override
    public Set<Post> findByUserId(UUID userId) {
        return userDao.findById(userId).getPosts();
    }

    @Override
    public Post delete(UUID postId) {
        Post postToDelete = findById(postId);
        findByUserId(postToDelete.getUserId())
                .remove(postToDelete);
        return postToDelete;

    }


}
