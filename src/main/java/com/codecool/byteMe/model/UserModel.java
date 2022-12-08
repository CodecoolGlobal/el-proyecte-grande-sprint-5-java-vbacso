package com.codecool.byteMe.model;

import com.codecool.byteMe.model.postable.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "users")
public class UserModel {

    private final LocalDate regDate = LocalDate.now();
    @Id
    @GeneratedValue(generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 1)
    private Long id;
    private String name;
    private int age;

    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean isAccountNonExpired = true;
    @JsonIgnore
    private boolean isAccountNonLocked = true;
    @JsonIgnore
    private boolean isCredentialsNonExpired = true;
    @JsonIgnore
    private boolean isEnabled = true;

    @JsonIncludeProperties({"id", "name", "profilePictureId"})
    @ManyToMany
    @JoinTable(
            name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_user_id"))
    private List<UserModel> friendList;

    private Long profilePictureId;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Image> images;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Post> posts;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Message> sendedMessages;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Message> receivedMessages;

    public UserModel(RegistrationUserModel user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.age = user.getAge();
        this.email = user.getEmail();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
