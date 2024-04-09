package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.UsersDto;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Operation(
            summary = "Create a user",
            description = "Create a user with informations (name, mail, password)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @PostMapping("")
    public ResponseEntity<UsersDto> createUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.saveUser(user));
    }

    @Operation(
            summary = "Get a user",
            description = "Get a user information with an ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getUser(@PathVariable int id) {
        return ResponseEntity.ok(usersService.getAUser(id));
    }
}
