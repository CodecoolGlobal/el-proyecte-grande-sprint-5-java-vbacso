package com.codecool.byteMe.controller;


import com.codecool.byteMe.model.User;
import com.codecool.byteMe.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAll")
    public Set<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/update/{userName}")
    public void updateName(
            @RequestBody User user,
            @PathVariable String userName) {

    }

    @GetMapping("/find")
    public User find(
            @RequestBody User user) {
        return userService.findByEmail(user.getEmail());
    }
}
