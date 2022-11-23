package com.codecool.byteMe.config;

import com.codecool.byteMe.dao.*;
import com.codecool.byteMe.model.Group;
import com.codecool.byteMe.model.Image;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
                .password("password")
                .build();
    }

    @Bean
    public User zeno() {
        return User.builder()
                .age(12)
                .email("zeno@byte.me")
                .name("Fergencs Zeno")
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
    public Post zenoFishingGroupFirstPostByZeno() {
        return Post.builder()
                .title("I caught a 20 kg carp")
                .body("I spent a week fishing at lake Balaton")
                .user(zeno())
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
                .body("The Boys")
                .user(dani())
                .post(vandaFirstPost())
                .build();
    }

    @Bean
    public Comment daniFirstComment3() {
        return Comment.builder()
                .body("I  you into anime: Sword Art Online")
                .user(dani())
                .post(vandaFirstPost())
                .build();
    }

    @Bean
    public Comment zenoFishingGroupFirstCommentByDani(){
        return Comment.builder()
                .body("Which area have you been fishing?")
                .user(dani())
                .post(zenoFishingGroupFirstPostByZeno())
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
    public Image zenoProfilePicture() {
        try {
            return Image.builder()
                    .content(Files.readAllBytes(new File("src/main/resources/static/zeno.jpg").toPath()))
                    .user(zeno())
                    .build();
        } catch (IOException e) {
            System.out.println("An error occurred");
            return null;
        }
    }

    @Bean
    public Image vandaProfilePicture() {
        try {
            return Image.builder()
                    .content(Files.readAllBytes(new File("src/main/resources/static/vanda.jpg").toPath()))
                    .user(vanda())
                    .build();
        } catch (IOException e) {
            System.out.println("An error occurred");
            return null;
        }
    }

    @Bean
    public Image erikProfilePicture() {
        try {
            return Image.builder()
                    .content(Files.readAllBytes(new File("src/main/resources/static/erik.jpg").toPath()))
                    .user(erik())
                    .build();
        } catch (IOException e) {
            System.out.println("An error occurred");
            return null;
        }
    }

    @Bean
    public Image daniProfilePicture() {
        try {
            return Image.builder()
                    .content(Files.readAllBytes(new File("src/main/resources/static/dani.jpg").toPath()))
                    .user(dani())
                    .build();
        } catch (IOException e) {
            System.out.println("An error occurred");
            return null;
        }
    }

    @Bean
    public Group zenoFishingGroup() {
        return Group.builder()
                .owner(zeno())
                .name("Zeno fishing group")
                .members(List.of(zeno(), vanda(), dani()))
                .build();
    }

    @Bean
    public Group daniWitcherGroup() {
        return Group.builder()
                .owner(dani())
                .name("Dani Witcher group")
                .members(List.of(dani(), zeno(), vanda()))
                .build();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        PostRepository postRepository,
                                        CommentRepository commentRepository,
                                        ImageRepository imageRepository,
                                        GroupRepository groupRepository) {
        return args -> {
            userRepository.save(vanda());
            userRepository.save(zeno());
            userRepository.save(erik());
            userRepository.save(dani());

            vanda().setFriendList(List.of(zeno()));
            zeno().setFriendList(List.of(vanda()));

            userRepository.save(vanda());
            userRepository.save(zeno());

            imageRepository.save(zenoProfilePicture());
            imageRepository.save(vandaProfilePicture());
            imageRepository.save(erikProfilePicture());
            imageRepository.save(daniProfilePicture());

            User updateZeno = userRepository.findById(zeno().getId()).get();
            updateZeno.setProfilePictureId(zenoProfilePicture().getId());
            userRepository.save(updateZeno);

            User updateVanda = userRepository.findById(vanda().getId()).get();
            updateVanda.setProfilePictureId(vandaProfilePicture().getId());
            userRepository.save(updateVanda);

            User updateErik = userRepository.findById(erik().getId()).get();
            updateErik.setProfilePictureId(erikProfilePicture().getId());
            userRepository.save(updateErik);

            User updateDani = userRepository.findById(dani().getId()).get();
            updateDani.setProfilePictureId(daniProfilePicture().getId());
            userRepository.save(updateDani);

            postRepository.save(zenoFirstPost());
            postRepository.save(zenoSecondPost());
            postRepository.save(vandaFirstPost());
            postRepository.save(zenoFishingGroupFirstPostByZeno());

            commentRepository.save(daniFirstComment());
            commentRepository.save(daniFirstComment2());
            commentRepository.save(daniFirstComment3());
            commentRepository.save(erikFirstComment());
            commentRepository.save(zenoFishingGroupFirstCommentByDani());

            Group zenoFishingGroup = zenoFishingGroup();
            groupRepository.save(zenoFishingGroup);

            Group updatedGroup = groupRepository.findById(zenoFishingGroup.getId()).get();
            updatedGroup.setPosts(List.of(zenoFishingGroupFirstPostByZeno()));
            groupRepository.save(updatedGroup);

            groupRepository.save(daniWitcherGroup());
        };
    }
}