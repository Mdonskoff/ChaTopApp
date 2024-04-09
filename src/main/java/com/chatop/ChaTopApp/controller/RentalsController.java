package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.AllRentalsDto;
import com.chatop.ChaTopApp.dto.RentalsDto;
import com.chatop.ChaTopApp.dto.ResponseDto;
import com.chatop.ChaTopApp.service.RentalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;

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
    public ResponseEntity<ResponseDto> createRental(@RequestParam HashMap<String, String> rentalInfo, @RequestParam("picture")MultipartFile picture) {

        if (rentalsService.createRental(rentalInfo, picture) != null) {
            return ResponseEntity.ok(new ResponseDto("Rental created !"));
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
    public ResponseEntity<ResponseDto> putRental(@PathVariable int id, @RequestParam HashMap<String, String> infoRental) {
        if (rentalsService.updateARental(id, infoRental) != null) {
            return ResponseEntity.ok(new ResponseDto("Rental updated !"));
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
    public ResponseEntity<RentalsDto> getRentalById(@PathVariable int id) {
        return ResponseEntity.ok(rentalsService.getARentalDto(id));
    }

    @Operation(
            summary = "Get all Rental",
            description = "Get a list of all rental ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("")
    public ResponseEntity<AllRentalsDto> getAllRentals() {
        return ResponseEntity.ok(new AllRentalsDto(rentalsService.getAllRentals()));
    }
}
