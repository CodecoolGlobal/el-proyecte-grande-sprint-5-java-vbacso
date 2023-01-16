package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.Message;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private UserService userService;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, UserService userService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userService = userService;
    }

    @MessageMapping("/message")
    public void receivePrivateMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(),"/private",message); // /user/{userId}/private
    }

    @EventListener
    public void handleUserConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        System.out.println(event);
        String userId = headers.getFirstNativeHeader("userId");
        List<Long> friendIds = userService.getFriendIds(Long.valueOf(userId));
        Message message = new Message();
        message.setStatus(Message.MessageStatus.ONLINE);
        message.setContent(userId);
        friendIds.forEach(friendId ->
                simpMessagingTemplate.convertAndSendToUser(friendId.toString(), "/private", message) // /user/{userId}/private
        );
    }

    @EventListener
    public void handleUserDisconnected(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        System.out.println(event);
//        String userId = headers.getFirstNativeHeader("userId");
//        List<Long> friendIds = userService.getFriendIds(Long.valueOf(userId));
//        Message message = new Message();
//        message.setStatus(Message.MessageStatus.ONLINE);
//        message.setContent(userId);
//        friendIds.forEach(friendId ->
//                simpMessagingTemplate.convertAndSendToUser(friendId.toString(), "/private", message) // /user/{userId}/private
//        );
    }


}
