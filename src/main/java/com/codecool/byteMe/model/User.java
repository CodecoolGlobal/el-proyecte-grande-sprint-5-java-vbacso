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
    private List<User> friendList;
    private String profilePic;
    @OneToMany
    private List<Post> posts;
}
