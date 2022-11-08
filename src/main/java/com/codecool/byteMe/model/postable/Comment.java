package com.codecool.byteMe.model.postable;

import com.codecool.byteMe.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Postable {

    public static final int VOTE = 0;
    @JsonIgnore
    @ManyToOne
    private Post post;

    @Builder
    public Comment(User user, String body, Post post) {
        super(user, body);
        this.post = post;
    }
}
