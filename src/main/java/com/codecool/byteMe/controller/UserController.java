package com.codecool.byteMe.controller;


import com.codecool.byteMe.config.Config;
import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
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

    @GetMapping("/find")
    public User find(
            @RequestBody User user){
        return userDaoMem.find(user.getEmail());
    }
}
