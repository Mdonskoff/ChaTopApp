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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Users createUser(@RequestBody Users user) {
        return usersService.saveUser(user);
    }

    @Operation(
            summary = "Get a user",
            description = "Get a user information with an ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("/{id}")
    public UsersDto getUser(@PathVariable int id) {
        return usersService.getAUser(id);
    }

    @Operation(
            summary = "Get all user",
            description = "Get a list of all users informations")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

}
