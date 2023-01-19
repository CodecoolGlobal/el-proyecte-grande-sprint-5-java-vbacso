package com.codecool.byteMe.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(generator = "image_sequence")
    @SequenceGenerator(name = "image_sequence", allocationSize = 1)
    protected Long id;
    @JsonIncludeProperties({"id", "name"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected UserModel user;
    byte[] content;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", user=" + user +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
