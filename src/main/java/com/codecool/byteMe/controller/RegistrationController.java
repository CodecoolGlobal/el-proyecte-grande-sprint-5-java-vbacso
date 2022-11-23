package com.codecool.byteMe.controller;


import com.codecool.byteMe.model.User;
import com.codecool.byteMe.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registration")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User registration(@RequestBody User user) {
        return userService.add(user);
    }
}
