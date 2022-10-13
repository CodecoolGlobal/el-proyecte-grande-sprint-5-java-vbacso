package com.codecool.byteMe.model;

import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.*;

public class User {


    private UUID id;
    private String name;
    private int age;
    private String email;
    private LocalDate regDate;
    private List<User> friendList;
    private Image profilePic;
    private Set<Post> posts;
    private Set<Comment> comments;


    public User(String name, int age, String email) {
        id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.email = email;
        this.regDate = regDate;
        this.regDate = LocalDate.now();
        //profilePic = ImageIO.read(new File("src/main/resources/static/img.png"));
        friendList = new ArrayList<>();
        posts = new HashSet<>();
        comments = new HashSet<>();
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

    public void addComment(Comment comment) {
        comments.add(comment);
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

    public List<User> getFriendList() {
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
