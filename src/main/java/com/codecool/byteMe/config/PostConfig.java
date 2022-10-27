package com.codecool.byteMe.config;

import com.codecool.byteMe.model.postable.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Configuration
public class PostConfig {

    @Bean
    public Post zenoFirstPost() {
        return new Post(UUID.nameUUIDFromBytes("zeno@byte.me".getBytes(StandardCharsets.UTF_8)), "Zeno's first post", "That's my first post", "Zeno");
    }

    @Bean
    public Post zenoSecondPost() {
        return new Post(UUID.nameUUIDFromBytes("zeno@byte.me".getBytes(StandardCharsets.UTF_8)), "Zeno's second post", "That's my second post", "Zeno");
    }

}
