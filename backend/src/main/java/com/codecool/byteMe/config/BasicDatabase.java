package com.codecool.byteMe.config;

import com.codecool.byteMe.dao.CommentRepository;
import com.codecool.byteMe.dao.ImageRepository;
import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.Image;
import com.codecool.byteMe.model.UserModel;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class BasicDatabase {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserModel vanda() {
        return UserModel.builder()
                .age(18)
                .email("vanda@byte.me")
                .name("Bacso Vanda")
                .password(passwordEncoder.encode("password"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .build();
    }

    @Bean
    public UserModel zeno() {
        return UserModel.builder()
                .age(12)
                .email("zeno@byte.me")
                .name("Fergencs Zeno")
                .password(passwordEncoder.encode("password"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .build();
    }

    @Bean
    public UserModel erik() {
        return UserModel.builder()
                .age(13)
                .email("erik@byte.me")
                .name("Izeli Erik")
                .password(passwordEncoder.encode("password"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .build();
    }

    @Bean
    public UserModel dani() {
        return UserModel.builder()
                .age(69420)
                .email("dani@byte.me")
                .name("Fekete Daniel")
                .password(passwordEncoder.encode("password"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
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

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository,
//                                        PostRepository postRepository,
//                                        CommentRepository commentRepository,
//                                        ImageRepository imageRepository) {
//        return args -> {
//            userRepository.save(vanda());
//            userRepository.save(zeno());
//            userRepository.save(erik());
//            userRepository.save(dani());
//
//            vanda().setFriendList(List.of(zeno()));
//            zeno().setFriendList(List.of(vanda()));
//
//            userRepository.save(vanda());
//            userRepository.save(zeno());
//
//            imageRepository.save(zenoProfilePicture());
//            imageRepository.save(vandaProfilePicture());
//            imageRepository.save(erikProfilePicture());
//            imageRepository.save(daniProfilePicture());
//
//            UserModel updateZeno = userRepository.findById(zeno().getId()).get();
//            updateZeno.setProfilePictureId(zenoProfilePicture().getId());
//            userRepository.save(updateZeno);
//
//            UserModel updateVanda = userRepository.findById(vanda().getId()).get();
//            updateVanda.setProfilePictureId(vandaProfilePicture().getId());
//            userRepository.save(updateVanda);
//
//            UserModel updateErik = userRepository.findById(erik().getId()).get();
//            updateErik.setProfilePictureId(erikProfilePicture().getId());
//            userRepository.save(updateErik);
//
//            UserModel updateDani = userRepository.findById(dani().getId()).get();
//            updateDani.setProfilePictureId(daniProfilePicture().getId());
//            userRepository.save(updateDani);
//
//            postRepository.save(zenoFirstPost());
//            postRepository.save(zenoSecondPost());
//            postRepository.save(vandaFirstPost());
//
//            commentRepository.save(daniFirstComment());
//            commentRepository.save(daniFirstComment2());
//            commentRepository.save(daniFirstComment3());
//            commentRepository.save(erikFirstComment());
//        };
//    }
}
