package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.Message;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private UserService userService;

    private Set<Long> onlineUsers = new HashSet<>();

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, UserService userService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userService = userService;
    }

    @MessageMapping("/message")
    public void receivePrivateMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(),"/private",message); // /user/{userId}/private
    }
    @MessageMapping("/login")
    public void handleLogin(@Payload Message login) {
        Long senderId = login.getSender().getId();
        onlineUsers.add(senderId);
        Message message = new Message();
        message.setStatus(Message.MessageStatus.ONLINE);
        message.setContent(String.valueOf(senderId));
        userService.getFriendIds(senderId).forEach(friendId ->{
            if (onlineUsers.contains(friendId)) {
                simpMessagingTemplate.convertAndSendToUser(friendId.toString(), "/private", message); // /user/{userId}/private
            }
        });
    }
    @MessageMapping("/logout")
    public void handleLogout(@Payload Message logout) {
        Long senderId = logout.getSender().getId();
        onlineUsers.remove(senderId);
        Message message = new Message();
        message.setStatus(Message.MessageStatus.OFFLINE);
        message.setContent(String.valueOf(senderId));
        userService.getFriendIds(senderId).forEach(friendId ->{
            if (onlineUsers.contains(friendId)) {
                simpMessagingTemplate.convertAndSendToUser(friendId.toString(), "/private", message); // /user/{userId}/private
            }
        });
    }
}
