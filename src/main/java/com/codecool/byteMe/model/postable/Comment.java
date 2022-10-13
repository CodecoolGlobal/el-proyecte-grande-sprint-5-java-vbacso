package com.codecool.byteMe.model.postable;

import java.util.UUID;

public class Comment extends Postable {

    public static final int VOTE = 0;
    private UUID postId;

    public Comment(String body, UUID postId, UUID userId) {
        super(userId, body, VOTE);
        this.postId = postId;
    }


}
