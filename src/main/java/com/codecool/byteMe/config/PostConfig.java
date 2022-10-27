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
        return new Post(UUID.nameUUIDFromBytes("zeno@byte.me".getBytes(StandardCharsets.UTF_8)), "It's the best day ever!", "I just caught a 25kg fish!", "");
    }

    @Bean
    public Post zenoSecondPost() {
        return new Post(UUID.nameUUIDFromBytes("zeno@byte.me".getBytes(StandardCharsets.UTF_8)), "The day got even better!!", "Another fish came! 35 kg this time! I'm so happy!!", "");
    }

}
