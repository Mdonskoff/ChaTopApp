package com.chatop.ChaTopApp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RentalsDto {

    private int id;

    private String name;

    private float surface;

    private float price;

    private String picture;

    private String description;

    private int owner_id;

    private Date created_at;

    private Date updated_at;
}
