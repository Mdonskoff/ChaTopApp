package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.MessagesDto;
import com.chatop.ChaTopApp.model.Messages;
import com.chatop.ChaTopApp.service.MessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @PostMapping("/messages")
    public HashMap<String, String> createMessage (@RequestBody MessagesDto messagesDto) {
        HashMap<String, String> infoMessage = new HashMap<>();
        infoMessage.put("message", "Message send with success");
        messagesService.saveMessage(messagesDto);
        return infoMessage;
    }


}
