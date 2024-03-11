package com.chatop.ChaTopApp.dto;

import lombok.Data;

@Data
public class MessagesDto {

    private String message;

    private int user_id;

    private int rental_id;
}
