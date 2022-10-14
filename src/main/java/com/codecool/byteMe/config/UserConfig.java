package com.codecool.byteMe.config;

import com.codecool.byteMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    PostConfig postConfig;

    @Autowired
    public void setPostConfig(PostConfig postConfig) {
        this.postConfig = postConfig;
    }

    @Bean
    public User zeno() {
        User zeno = new User("Zénó", 18, "zeno@byte.me");
        zeno.addPost(postConfig.zenoFirstPost());
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
