package com.chatop.ChaTopApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllRentalsDto {

    private List<RentalsDto> rentals;
}
