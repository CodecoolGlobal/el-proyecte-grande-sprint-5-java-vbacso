package com.codecool.byteMe.Class;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Page {

    private List<User> users;

    public Page() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void updateName(User user, String name){
        for(User updateUser : users){
            if(updateUser.getName().equals(user.getName())){
                updateUser.setName(name);
            }
        }
    }

    public List<Post> displayPosts() {
        List<Post> posts = new ArrayList<>();
        for (User user : users) {
            posts.addAll(user.getPosts());
        }
        return posts;
    }
}
