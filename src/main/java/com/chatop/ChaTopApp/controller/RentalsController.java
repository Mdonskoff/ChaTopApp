package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.RentalsDto;
import com.chatop.ChaTopApp.service.RentalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Create a rental",
            description = "Create a rental with informations (surface, price, description) and a picture." +
                    "If there is no name then a name will be given by default ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @PostMapping(value = "", consumes = "multipart/form-data")
    public HashMap<String, String> createRental(@RequestParam HashMap<String, String> rentalInfo, @RequestParam("picture")MultipartFile picture) {

        if (rentalsService.createRental(rentalInfo, picture) != null) {
            HashMap<String, String> infoMessage = new HashMap<>();
            infoMessage.put("message", "Rental created !");
            return infoMessage;
        }
        return null;
    }
    @Operation(
            summary = "Update a rental",
            description = "Update a rental informations (name, surface, price, description)." +
                    "If there is no name then a name will be given by default ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
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

    @Operation(
            summary = "Get a rental",
            description = "Get the informations of a rental ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("/{id}")
    public RentalsDto getRentalById(@PathVariable int id) {
        return rentalsService.getARentalDto(id);
    }

    @Operation(
            summary = "Get all Rental",
            description = "Get a list of all rental ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("")
    public HashMap <String, List<RentalsDto>> getAllRentals() {
        HashMap<String, List<RentalsDto>> allRentalsDto = new HashMap<>();
        List<RentalsDto> rentalsDtoList = rentalsService.getAllRentals();
        allRentalsDto.put("rentals", rentalsDtoList);
        return allRentalsDto;
    }
}
