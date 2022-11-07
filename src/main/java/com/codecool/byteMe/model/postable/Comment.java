package com.codecool.byteMe.model.postable;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Postable {

    public static final int VOTE = 0;
    @ManyToOne
    private Post post;
}
