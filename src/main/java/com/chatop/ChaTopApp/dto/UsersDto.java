package com.chatop.ChaTopApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {

    private int id;

    private String name;

    private String email;

    private Date created_at;

    private Date updated_at;
}
