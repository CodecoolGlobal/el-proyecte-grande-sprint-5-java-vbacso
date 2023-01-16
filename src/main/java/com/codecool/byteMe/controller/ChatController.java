package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/message")
    public void receivePrivateMessage(@Payload Message message) {
        System.out.println(message);

        simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(),"/private",message); // /user/{userId}/private
    }

    @MessageMapping("/login")
    public void login(@Payload Message message) {
        System.out.println(message);
    }
}
