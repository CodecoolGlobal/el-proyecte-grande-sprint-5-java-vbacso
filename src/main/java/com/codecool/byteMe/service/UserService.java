package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User add(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        System.out.println(userRepository.findByEmail(email));
        return userRepository.findByEmail(email);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user with given id"));
    }

    public User edit(User user) {
        User updatableUser = userRepository.findById(user.getId()).get();
        updatableUser.setName(user.getName());
        updatableUser.setAge(user.getAge());
        updatableUser.setEmail(user.getEmail());
        return userRepository.save(updatableUser);
    }

//    public User findByIdAdd(UUID userId, Post post) {
//        return userRepository.findByIdAdd(userId, post);
//    }
//
//    public User findByIdDelete(UUID userId, UUID postId) {
//        return userRepository.findByIdDelete(userId, postId);
//    }
}
