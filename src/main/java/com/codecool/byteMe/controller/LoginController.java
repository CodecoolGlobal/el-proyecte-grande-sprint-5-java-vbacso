package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.UserDTO;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO login(@RequestBody User user) {
        return new UserDTO(userService.findByEmail(user.getEmail()));
    }
}
