package com.codecool.byteMe.Class;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    String imagePath = "src/main/java/resources/static/img.png";
    BufferedImage myPicture = ImageIO.read(new File(imagePath));
    private UUID id;
    private String name;
    private int age;
    private LocalDate regDate;
    private List<User> friendList;
    private Image profilePic;
    private List<Post> posts;


    public User(String name, int age, LocalDate regDate) throws IOException {
        id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.regDate = regDate;
        profilePic = myPicture;
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

    public int getAge() {
        return age;
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
