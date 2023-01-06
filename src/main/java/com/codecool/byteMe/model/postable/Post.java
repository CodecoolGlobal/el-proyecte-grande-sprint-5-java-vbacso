package com.codecool.byteMe.model.postable;

import com.codecool.byteMe.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends Postable {

    public static final int VOTE = 0;
    private String title;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    public Post(UserModel user, String body, String title, LocalDateTime created) {
        super(user, body, created);
        this.title = title;
    }

    public Post(UserModel user, String body, String title) {
        super(user, body);
        this.title = title;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", body='" + body + '\'' +
                ", vote=" + vote +
                ", created=" + created +
                '}';
    }
}
