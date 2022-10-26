package com.codecool.byteMe.model;

import com.codecool.byteMe.model.postable.Post;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {


    private UUID id;
    private String name;
    private int age;
    private String email;
    private LocalDate regDate;
    private Set<UUID> friendList;
    private Image profilePic;
    private Set<Post> posts;


    public User(String name, int age, String email) {
        id = UUID.nameUUIDFromBytes(email.getBytes(StandardCharsets.UTF_8));
        this.name = name;
        this.age = age;
        this.email = email;
        this.regDate = LocalDate.now();
        //profilePic = ImageIO.read(new File("src/main/resources/static/img.png"));
        friendList = new HashSet<>();
        posts = new HashSet<>();
    }

    public void addFriend(User user) {
        friendList.add(user.getId());
    }

    public void deleteFriend(User user) {
        friendList.remove(user.getId());
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }

    public void updateName(User user) {

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

    public Set<UUID> getFriendList() {
        return friendList;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public String getEmail() {
        return email;
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
