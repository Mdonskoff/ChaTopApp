package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.MessagesDto;
import com.chatop.ChaTopApp.dto.ResponseDto;
import com.chatop.ChaTopApp.service.MessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @Operation(
            summary = "Post a message",
            description = "A user can post a message about a rental")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "401")
    })
    @PostMapping("/messages")
    public ResponseEntity<ResponseDto> createMessage (@RequestBody MessagesDto messagesDto) {
        messagesService.saveMessage(messagesDto);
        return ResponseEntity.ok(new ResponseDto("Message send with success"));
    }


}
