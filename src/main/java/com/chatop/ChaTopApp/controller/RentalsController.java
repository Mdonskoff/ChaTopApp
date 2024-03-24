package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.RentalsDto;
import com.chatop.ChaTopApp.model.Rentals;
import com.chatop.ChaTopApp.service.RentalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;

    @PostMapping(value = "", consumes = "multipart/form-data")
    public HashMap<String, String> createRental(@RequestParam HashMap<String, String> rentalInfo, @RequestParam("picture")MultipartFile picture) {

        if (rentalsService.createRental(rentalInfo, picture) != null) {
            HashMap<String, String> infoMessage = new HashMap<>();
            infoMessage.put("message", "Rental created !");
            return infoMessage;
        }
        return null;
    }

    @PutMapping(value = "/{id}")
    public HashMap<String, String> putRental(@PathVariable int id, @RequestParam HashMap<String, String> infoRental) {
        log.info(String.valueOf(infoRental));
        if (rentalsService.updateARental(id, infoRental) != null) {
            HashMap<String, String> infoMessage = new HashMap<>();
            infoMessage.put("message", "Rental updated !");
            return infoMessage;
        }
        return null;
    }


    @GetMapping("/{id}")
    public RentalsDto getRentalById(@PathVariable int id) {
        return rentalsService.getARentalDto(id);
    }
    @GetMapping("")
    public HashMap <String, List<RentalsDto>> getAllRentals() {
        HashMap<String, List<RentalsDto>> allRentalsDto = new HashMap<>();
        List<RentalsDto> rentalsDtoList = rentalsService.getAllRentals();
        allRentalsDto.put("rentals", rentalsDtoList);
        return allRentalsDto;
    }
}
