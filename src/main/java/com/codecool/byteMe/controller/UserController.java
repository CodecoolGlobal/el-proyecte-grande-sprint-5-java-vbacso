package com.codecool.byteMe.controller;


import com.codecool.byteMe.dao.UserInfo;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.edit(user);
    }

    @GetMapping("/findById/{userId}")
    public User findUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/findByEmail")
    public User findUserByEmail(@RequestBody User user) {
        return userService.findByEmail(user.getEmail());
    }

    @DeleteMapping("/delete")
    public User deleteUser(@RequestBody User user) {
        return deleteUser(user);
    }

    @GetMapping("search/{userName}")
    List<UserInfo> FindByNameLike(@PathVariable String userName) {
        return userService.findByNameLike(userName);
    }
}
