package com.codecool.byteMe.controller;


import com.codecool.byteMe.model.User;
import com.codecool.byteMe.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Set<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.edit(user);
    }

    @GetMapping("/findById")
    public User findUserById(@RequestBody User user) {
        return userService.findById(user.getId());
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
