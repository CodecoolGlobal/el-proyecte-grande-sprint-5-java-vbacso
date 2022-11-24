package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserInfo;
import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel add(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModel findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user with given id"));
    }

    public UserModel edit(UserModel user) {
        UserModel updatableUser = userRepository.findById(user.getId()).get();
        if (Boolean.parseBoolean(user.getName()))updatableUser.setName(user.getName());
        if (user.getAge() != 0)updatableUser.setAge(user.getAge());
        return userRepository.save(updatableUser);
    }

    public List<UserInfo> findByNameLike(String userName) {
        return userRepository.findByNameIsContainingAllIgnoreCase(userName);
    }

    public void addFriend(Long user1Id, Long user2Id) {
        UserModel updatableUser1 = userRepository.findById(user1Id).get();
        UserModel updatableUser2 = userRepository.findById(user2Id).get();
        List<UserModel> updatableUser1FriendList = updatableUser1.getFriendList();
        List<UserModel> updatableUser2FriendList = updatableUser2.getFriendList();

        updatableUser1FriendList.add(updatableUser2);
        updatableUser2FriendList.add(updatableUser1);

        updatableUser1.setFriendList(updatableUser1FriendList);
        updatableUser2.setFriendList(updatableUser2FriendList);

        userRepository.save(updatableUser1);
        userRepository.save(updatableUser2);
    }

    public void deleteFriend(Long user1Id, Long user2Id) {
        UserModel updatableUser1 = userRepository.findById(user1Id).get();
        UserModel updatableUser2 = userRepository.findById(user2Id).get();
        List<UserModel> updatableUser1FriendList = updatableUser1.getFriendList();
        List<UserModel> updatableUser2FriendList = updatableUser2.getFriendList();

        updatableUser1FriendList.remove(updatableUser2);
        updatableUser2FriendList.remove(updatableUser1);

        updatableUser1.setFriendList(updatableUser1FriendList);
        updatableUser2.setFriendList(updatableUser2FriendList);

        userRepository.save(updatableUser1);
        userRepository.save(updatableUser2);
    }
}
