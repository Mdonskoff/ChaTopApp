package com.chatop.ChaTopApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogInDto {
    private String email;
    private String password;

}
