package com.codecool.byteMe.model.postable;

import com.codecool.byteMe.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Postable {
    @Id
    @GeneratedValue
    protected Long id;
    @ManyToOne
    protected User user;
    protected String username;
    protected String body;
    protected int vote;
    protected LocalDateTime created = LocalDateTime.now();
}
