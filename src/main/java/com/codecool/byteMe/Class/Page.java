package com.codecool.byteMe.Class;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private List<User> users;

    public Page() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Post> displayPosts() {
        List<Post> posts = new ArrayList<>();
        for (User user : users) {
            posts.addAll(user.getPosts());
        }
        return posts;
    }
}
