package com.codecool.byteMe.controller;


import com.codecool.byteMe.dao.UserInfo;
import com.codecool.byteMe.model.UserModel;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserModel> getAllUser() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public UserModel add(@RequestBody UserModel user) {
        return userService.add(user);
    }

    @PutMapping("/update")
    public UserModel update(@RequestBody UserModel user) {
        return userService.edit(user);
    }

    @GetMapping("/findById/{userId}")
    public UserModel findUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/findByEmail/{email}")
    public UserModel findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @DeleteMapping("/delete")
    public UserModel deleteUser(@RequestBody UserModel user) {
        return deleteUser(user);
    }

    @GetMapping("search/{userName}")
    public List<UserInfo> findByNameLike(@PathVariable String userName) {
        return userService.findByNameLike(userName);
    }

    @PutMapping("{user1Id}/{user2Id}")
    public void addFriend(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        userService.addFriend(user1Id, user2Id);
    }

    @DeleteMapping("/{user1Id}/{user2Id}")
    public void deleteFriend(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        userService.deleteFriend(user1Id, user2Id);
    }

    @GetMapping("/friends/{userId}")
    public List<User> findUserFriendListByUserId(@PathVariable Long userId) {
        return userService.findUserFriendListByUserId(userId);
    }
}
