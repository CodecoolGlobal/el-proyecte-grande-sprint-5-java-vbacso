package com.codecool.byteMe.controller;


import com.codecool.byteMe.model.User;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
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
    public User findUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

//    @PostMapping("/findById/{userId}/add")
//    @CrossOrigin(origins = "*")
//    public User findUserByIdAdd(@RequestBody Post post, @PathVariable UUID userId) {
//        return userService.findByIdAdd(userId, post);
//    }

//    @DeleteMapping("/findById/{userId}/delete")
//    @CrossOrigin(origins = "*")
//    public User findUserByIdDelete(@RequestBody UUID postId, @PathVariable UUID userId) {
//        return userService.findByIdDelete(userId, postId);
//    }

    @GetMapping("/findByEmail")
    public User findUserByEmail(@RequestBody User user) {
        return userService.findByEmail(user.getEmail());
    }

    @DeleteMapping("/delete")
    public User deleteUser(@RequestBody User user) {
        return deleteUser(user);
    }
}
