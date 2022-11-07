package com.codecool.byteMe.model;

import com.codecool.byteMe.model.postable.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private String email;
    private LocalDate regDate;
    @ManyToMany
    @JoinTable(
            name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_user_id"))
    private List<User> friendList;
    private String profilePic;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Post> posts;
}
