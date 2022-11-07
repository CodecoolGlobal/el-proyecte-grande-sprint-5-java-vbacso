package com.codecool.byteMe.model.postable;

import com.codecool.byteMe.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "user_id")
    protected User user;
    protected String body;
    protected int vote;
    protected LocalDateTime created = LocalDateTime.now();
}
