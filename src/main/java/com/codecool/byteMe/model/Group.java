package com.codecool.byteMe.model;

import com.codecool.byteMe.model.postable.Post;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(generator = "group_sequence")
    @SequenceGenerator(name = "group_sequence", allocationSize = 1)
    private Long id;
    private String name;

    @ManyToOne
    private UserModel owner;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany
    @JoinColumn(name = "group_id")
    private List<Post> posts;

    @JsonIncludeProperties({"id", "profilePictureId"})
    @ManyToMany
    @JoinTable(
            name = "group_member",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserModel> members;


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", posts=" + posts +
                ", members=" + members +
                '}';
    }
}
