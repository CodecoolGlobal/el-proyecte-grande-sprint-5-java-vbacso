package com.codecool.byteMe.controller;


import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDaoMem;

    @GetMapping("/get")
    public Set<User> getUsers() {
        return userDaoMem.getAllUser();
    }

    @PostMapping("/add")
    public void addUsers(
            @RequestBody User user){
        userDaoMem.add(user);

    }

    @PutMapping("/update/{userName}")
    public void updateName(
            @RequestBody User user,
            @PathVariable String userName) {
    }
}
