package com.codecool.byteMe.model;

import com.codecool.byteMe.model.postable.Post;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {


    private UUID id;
    private String name;
    private int age;
    private LocalDate regDate;
    private List<User> friendList;
    private Image profilePic;
    private List<Post> posts;


    public User(String name, int age) {
        id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.regDate = LocalDate.now();
        //profilePic = ImageIO.read(new File("src/main/resources/static/img.png"));
        friendList = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public void addFriend(User user) {
        friendList.add(user);
    }

    public void deleteFriend(User user) {
        friendList.remove(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
