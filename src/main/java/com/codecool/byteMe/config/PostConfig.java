package com.codecool.byteMe.config;

import com.codecool.byteMe.model.postable.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class PostConfig {

    @Bean
    public Set<Post> getBaseDaoPosts() {
        return new HashSet<>();
    }

}
