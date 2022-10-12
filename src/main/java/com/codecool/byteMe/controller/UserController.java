package com.codecool.byteMe.controller;


import com.codecool.byteMe.model.User;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("getAll")
    public Set<User> getUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/add")
    public void addUsers(@RequestBody User user) {
        userService.add(user);
    }

    @PutMapping("/update/{userName}")
    public void updateName(@RequestBody User user, @PathVariable String userName) {
    }
}
