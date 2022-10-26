package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;



@Component
public class UserDaoMem implements UserDao {

    private Set<User> data;

    public UserDaoMem() {
        data = new HashSet<>();
    }

    @Autowired
    @Qualifier("baseUsers")
    public void setData(Set<User> data) {
        this.data = data;
    }

    @Override
    public Set<User> getAll() {
        return data;
    }

    @Override
    public User add(User user) {
        data.add(user);
        return user;
    }

    @Override
    public User edit(User user) {
        data.stream()
                .filter(userU -> userU.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .setName(user.getName());
        return user;
    }

    @Override
    public User findById(UUID userId) {
        return data.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User findByEmail(String email) {
        return data.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User delete(UUID userId) {
        User userToDelete = data.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        data.remove(userToDelete);
        return userToDelete;
    }

    @Override
    public User findByIdAdd(UUID userId, Post post) {
        User user = findById(userId);
        user.getPosts().add(post);
        return user;
    }

    @Override
    public User findByIdDelete(UUID userId, UUID postId) {
        User user = findById(userId);
        Post postToDelete = user.getPosts().stream().filter(post -> post.getId().equals(postId)).findFirst().orElseThrow(NoSuchElementException::new);

        user.deletePost(postToDelete);
        return user;
    }
}
