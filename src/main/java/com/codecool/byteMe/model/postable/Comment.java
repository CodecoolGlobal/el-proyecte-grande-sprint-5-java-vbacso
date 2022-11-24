package com.codecool.byteMe.model.postable;

import com.codecool.byteMe.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
    @JsonIncludeProperties("id")
    @ManyToOne
    private Post post;

    @Builder
    public Comment(UserModel user, String body, Post post) {
        super(user, body);
        this.post = post;
    }
}
