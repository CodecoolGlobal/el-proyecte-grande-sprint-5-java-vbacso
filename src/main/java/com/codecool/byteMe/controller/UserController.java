package com.codecool.byteMe.controller;


import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Set<User> getAllUser() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.edit(user);
    }

    @GetMapping("/findById/{userId}")
    @CrossOrigin(origins = "*")
    public User findUserById(@PathVariable UUID userId) {
        return userService.findById(userId);
    }

    @PostMapping("/findById/{userId}/add")
    @CrossOrigin(origins = "*")
    public User findUserByIdAdd(@RequestBody Post post, @PathVariable UUID userId) {
        return userService.findByIdAdd(userId, post);
    }

    @GetMapping("/findByEmail")
    public User findUserByEmail(@RequestBody User user) {
        return userService.findByEmail(user.getEmail());
    }

    @DeleteMapping("/delete")
    public User deleteUser(@RequestBody User user) {
        return deleteUser(user);
    }
}
