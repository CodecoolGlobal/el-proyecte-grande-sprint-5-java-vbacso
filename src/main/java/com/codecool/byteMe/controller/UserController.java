package com.codecool.byteMe.controller;



import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Page page;

    @GetMapping("/get")
    public List<User> getUsers(){
        return page.getUsers();
    }

    @PostMapping("/add")
    public void addUsers(
            @RequestBody User user){
        page.addUser(user);

    }

    @PutMapping("/update/{userName}")
    public void updateName(
            @RequestBody User user,
            @PathVariable String userName){
        page.updateName(user, userName);

    }
}
