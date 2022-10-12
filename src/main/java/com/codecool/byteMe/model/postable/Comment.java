package com.codecool.byteMe.model.postable;

public class Comment extends Postable {


    public static final int VOTE = 0;

    public Comment(String body) {
        super(body, VOTE);
    }


}
