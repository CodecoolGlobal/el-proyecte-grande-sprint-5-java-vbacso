package com.codecool.byteMe.model.postable;

import com.codecool.byteMe.model.Group;
import com.codecool.byteMe.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class Postable {
    @Id
    @GeneratedValue(generator = "postable_sequence")
    @SequenceGenerator(name = "postable_sequence", allocationSize = 1)
    protected Long id;

    @JsonIncludeProperties({"id", "name", "profilePictureId"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected UserModel user;

    @JsonIncludeProperties({"id"})
    @ManyToOne
    @JoinColumn(name = "group_id")
    protected Group group;
    protected String body;
    protected int vote = 0;

    @Builder.Default
    protected LocalDateTime created = LocalDateTime.now();

    public Postable(UserModel user, String body) {
        this.user = user;
        this.body = body;
    }

    public Postable(UserModel user, String body, LocalDateTime created) {
        this.user = user;
        this.body = body;
        this.created = created;
    }
}
