package com.codecool.byteMe.controller;


import com.codecool.byteMe.dao.AppDao;
import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AppDao appDaoMem;

    @GetMapping("/get")
    public Set<User> getUsers() {
        return appDaoMem.getAllUser();
    }

    @PostMapping("/add")
    public void addUsers(@RequestBody User user) {
        appDaoMem.addUser(user);
    }

    @PutMapping("/update/{userName}")
    public void updateName(@RequestBody User user, @PathVariable String userName) {

    }

    @GetMapping("/find")
    public User find(@RequestBody User user) {
        return appDaoMem.findUserByEmail(user.getEmail());
    }
}
