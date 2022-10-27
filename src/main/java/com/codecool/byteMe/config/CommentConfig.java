package com.codecool.byteMe.config;

import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Configuration
public class CommentConfig {

    @Bean
    public Comment daniFirstComment(Post zenoFirstPost) {
        return new Comment("Dani's first comment.",
                zenoFirstPost.getId(),
                UUID.nameUUIDFromBytes("dani@byte.me".getBytes(StandardCharsets.UTF_8)),
                "Dani");
    }
}
