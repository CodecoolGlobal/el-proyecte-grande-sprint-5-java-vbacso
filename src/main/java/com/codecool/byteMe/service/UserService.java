package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserInfo;
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
        return userRepository.findByEmail(email);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user with given id"));
    }

    public User edit(User user) {
        User updatableUser = userRepository.findById(user.getId()).get();
        if (Boolean.parseBoolean(user.getName()))updatableUser.setName(user.getName());
        if (user.getAge() != 0)updatableUser.setAge(user.getAge());
        return userRepository.save(updatableUser);
    }

    public List<UserInfo> findByNameLike(String userName) {
        return userRepository.findByNameIsContainingAllIgnoreCase(userName);
    }

    public void addFriend(Long user1Id, Long user2Id) {
        User updatableUser1 = userRepository.findById(user1Id).get();
        User updatableUser2 = userRepository.findById(user2Id).get();
        List<User> updatableUser1FriendList = updatableUser1.getFriendList();
        List<User> updatableUser2FriendList = updatableUser2.getFriendList();

        updatableUser1FriendList.add(updatableUser2);
        updatableUser2FriendList.add(updatableUser1);

        updatableUser1.setFriendList(updatableUser1FriendList);
        updatableUser2.setFriendList(updatableUser2FriendList);

        userRepository.save(updatableUser1);
        userRepository.save(updatableUser2);
    }

    public void deleteFriend(Long user1Id, Long user2Id) {
        User updatableUser1 = userRepository.findById(user1Id).get();
        User updatableUser2 = userRepository.findById(user2Id).get();
        List<User> updatableUser1FriendList = updatableUser1.getFriendList();
        List<User> updatableUser2FriendList = updatableUser2.getFriendList();

        updatableUser1FriendList.remove(updatableUser2);
        updatableUser2FriendList.remove(updatableUser1);

        updatableUser1.setFriendList(updatableUser1FriendList);
        updatableUser2.setFriendList(updatableUser2FriendList);

        userRepository.save(updatableUser1);
        userRepository.save(updatableUser2);
    }
}
