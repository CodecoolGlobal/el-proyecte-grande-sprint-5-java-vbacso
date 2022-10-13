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
    public User addUsers(@RequestBody User user) {
        return appDaoMem.addUser(user);
    }

    @PutMapping("/update/{userName}")
    public User updateName(@RequestBody User user) {
        return appDaoMem.editUserName(user);
    }

    @GetMapping("/findById")
    public User findUserById(@RequestBody User user) {
        return appDaoMem.findUserById(user.getId());
    }
    @GetMapping("/findById")
    public User findUserByEmail(@RequestBody User user) {
        return appDaoMem.findUserByEmail(user.getEmail());
    }

    @DeleteMapping("/delete")
    public User deleteUser(@RequestBody User user){
        return deleteUser(user);
    }
}
