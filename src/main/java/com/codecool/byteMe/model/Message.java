package com.codecool.byteMe.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(generator = "message_sequence")
    @SequenceGenerator(name = "message_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonIncludeProperties({"id", "name", "profilePictureId"})
    @JoinColumn(name = "sender_id")
    private UserModel sender;
    @ManyToOne
    @JsonIncludeProperties({"id", "name", "profilePictureId"})
    @JoinColumn(name = "receiver_id")
    private UserModel receiver;
    private LocalDateTime created;

    private String content;

    @Transient
    private MessageStatus status;

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", created=" + created +
                ", content='" + content + '\'' +
                '}';
    }

    public enum MessageStatus {
        MESSAGE,
        ONLINE,
        OFFLINE,
        PING
    }

}
