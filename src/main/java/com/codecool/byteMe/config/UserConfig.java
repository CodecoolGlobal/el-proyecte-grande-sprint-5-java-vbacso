package com.codecool.byteMe.config;

import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserConfig {

    PostConfig postConfig;

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
        User zeno = new User("Zénó", 18, "zeno@byte.me");
        zeno.addPost(postConfig.zenoFirstPost());
        zeno.addPost(postConfig.zenoSecondPost());
        return zeno;
    }

    @Bean
    public User vanda() {
        return new User("Vanda", 18, "vanda@byte.me");
    }

    @Bean
    public User erik() {
        return new User("Erik", 18, "erik@byte.me");
    }

    @Bean
    public User dani() {
        return new User("Dani", 69420, "dani@byte.me");
    }
}
