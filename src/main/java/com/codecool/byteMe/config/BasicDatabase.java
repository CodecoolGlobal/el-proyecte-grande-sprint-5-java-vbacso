package com.codecool.byteMe.config;

import com.codecool.byteMe.dao.CommentRepository;
import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class BasicDatabase {
    @Bean
    public User vanda() {
        return User.builder()
                .age(18)
                .email("vanda@byte.me")
                .name("Bacso Vanda")
                .build();
    }

    @Bean
    public User zeno() {
        return User.builder()
                .age(12)
                .email("zeno@byte.me")
                .name("Fergencs Zeno")
                .friendList(List.of(vanda()))
                .build();
    }

    @Bean
    public User erik() {
        return User.builder()
                .age(13)
                .email("erik@byte.me")
                .name("Izeli Erik")
                .build();
    }

    @Bean
    public User dani() {
        return User.builder()
                .age(69420)
                .email("dani@byte.me")
                .name("Fekete Daniel")
                .build();
    }

    @Bean
    public Post zenoFirstPost() {
        return Post.builder()
                .title("Today's catch")
                .body("Two IllegalArgumentExceptions and a 10kg catfish")
                .user(zeno())
                .created(LocalDateTime.of(2022, 8, 12, 16, 52, 12))
                .build();
    }

    @Bean
    public Post zenoSecondPost() {
        return Post.builder()
                .title("The day got even better!!")
                .body("Another fish came! 35 kg this time! I'm so happy!!")
                .user(zeno())
                .created(LocalDateTime.of(2022, 8, 12, 17, 23))
                .build();
    }

    @Bean
    public Post vandaFirstPost() {
        return Post.builder()
                .title("Boring Flower Power")
                .body("Pls recommend some good series")
                .user(vanda())
                .build();
    }

    @Bean
    public Comment daniFirstComment() {
        return Comment.builder()
                .body("Who believes it ? Where's the picture of it buddy?")
                .user(dani())
                .post(zenoFirstPost())
                .build();
    }

    @Bean
    public Comment daniFirstComment2() {
        return Comment.builder()
                .body("Test2 Test2 Test2 Test2 Test2 Test2 Test2 Test2 Test2 ")
                .user(dani())
                .post(vandaFirstPost())
                .build();
    }

    @Bean
    public Comment daniFirstComment3() {
        return Comment.builder()
                .body("Test3 Test3 Test3 Test3 Test3 Test3 Test3 Test3 Test3 ")
                .user(dani())
                .post(vandaFirstPost())
                .build();
    }

    @Bean
    public Comment erikFirstComment() {
        return Comment.builder()
                .body("Blue Mountain State")
                .user(erik())
                .post(vandaFirstPost())
                .build();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        PostRepository postRepository,
                                        CommentRepository commentRepository) {
        return args -> {
            userRepository.save(vanda());
            userRepository.save(zeno());
            userRepository.save(erik());
            userRepository.save(dani());

            postRepository.save(zenoFirstPost());
            postRepository.save(zenoSecondPost());
            postRepository.save(vandaFirstPost());

            commentRepository.save(daniFirstComment());
            commentRepository.save(daniFirstComment2());
            commentRepository.save(daniFirstComment3());
            commentRepository.save(erikFirstComment());
        };
    }
}