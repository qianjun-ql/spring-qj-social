package com.qj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.qj.models.Message;

@RestController
public class RealTimeChat {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{groupId}")
    public void sendToUser(@Payload Message message, @DestinationVariable String groupId) {
        System.out.println("Received message for group " + groupId + ": " + message);
        simpMessagingTemplate.convertAndSend("/user/" + groupId + "/private", message);
    }
}