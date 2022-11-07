package com.codecool.byteMe.model.postable;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends Postable {

    public static final int VOTE = 0;
    private String title;
    @OneToMany
    private List<Comment> comments;
}
