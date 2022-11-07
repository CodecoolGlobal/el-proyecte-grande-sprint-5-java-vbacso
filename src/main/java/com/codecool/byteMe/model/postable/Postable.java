package com.codecool.byteMe.model.postable;

import com.codecool.byteMe.model.User;
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
    @GeneratedValue
    protected Long id;
    @JsonIncludeProperties("name")
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;
    protected String body;
    protected int vote = 0;
    @Builder.Default
    protected LocalDateTime created = LocalDateTime.now();

    public Postable(User user, String body) {
        this.user = user;
        this.body = body;
    }

    public Postable(User user, String body, LocalDateTime created) {
        this.user = user;
        this.body = body;
        this.created = created;
    }
}
