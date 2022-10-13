package com.codecool.byteMe.model.postable;

import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class Postable {

    protected UUID id;
    protected UUID userId;
    protected String body;
    protected int vote;

    public Postable(UUID userId, String body, int vote) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.body = body;
        this.vote = vote;
    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    public void increaseVote() {
        vote++;
    }

    public void decreaseVote() {
        vote--;
    }

    public UUID getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getVote() {
        return vote;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "PostAbles{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", vote=" + vote +
                '}';
    }
}
