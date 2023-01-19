package com.codecool.byteMe.controller;


import com.codecool.byteMe.model.RegistrationUserModel;
import com.codecool.byteMe.model.UserModel;
import com.codecool.byteMe.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("registration")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Optional<UserModel> registration(@RequestBody RegistrationUserModel user) {
        String emailGiven = user.getEmail();
        if (userService.findByEmail(emailGiven) != null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(userService.add(new UserModel(user)));
        }
    }
}
