package com.codecool.byteMe.model.postable;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Postable {

    public static final int VOTE = 0;
    private Long postId;

}
