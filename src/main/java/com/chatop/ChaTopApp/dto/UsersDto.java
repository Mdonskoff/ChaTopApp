package com.chatop.ChaTopApp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UsersDto {

    private int id;

    private String name;

    private String email;

    private Date created_at;

    private Date updated_at;
}
