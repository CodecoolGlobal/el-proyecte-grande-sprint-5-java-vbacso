package com.codecool.byteMe.config;

import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserConfig {

    PostConfig postConfig;

    @Autowired
    CommentConfig commentConfig;

    @Autowired
    public void setPostConfig(PostConfig postConfig) {
        this.postConfig = postConfig;
    }

    @Bean
    Set<User> baseUsers() {
        Set<User> users = new HashSet<>();

        User vanda = vanda();
        User zeno = zeno();

        users.add(zeno);
        users.add(vanda);
        vanda.addFriend(zeno);
        zeno.addFriend(vanda);

        users.add(erik());
        users.add(dani());
        return users;
    }

    @Bean
    public User zeno() {
        User zeno = new User("Zénó Fergencs", 18, "zeno@byte.me");

        Post post = postConfig.zenoFirstPost();
        post.setUsername(zeno.getName());
        zeno.addPost(post);

        Comment comment = commentConfig.daniFirstComment(post);
        comment.setUsername(dani().getName());
        post.addComment(comment);


        Post secondPost = (postConfig.zenoSecondPost());
        secondPost.setUsername(zeno.getName());
        zeno.addPost(secondPost);

        return zeno;
    }

    @Bean
    public User vanda() {
        return new User("Vanda Bacsó", 18, "vanda@byte.me");
    }

    @Bean
    public User erik() {
        return new User("Erik Izeli", 18, "erik@byte.me");
    }

    @Bean
    public User dani() {
        return new User("Dániel Tivadar Fekete", 69420, "dani@byte.me");
    }
}
