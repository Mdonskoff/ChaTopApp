package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.LogInDto;
import com.chatop.ChaTopApp.dto.RegisterDto;
import com.chatop.ChaTopApp.dto.UsersDto;
import com.chatop.ChaTopApp.service.AuthService;
import com.chatop.ChaTopApp.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsersService usersService;

    @Operation(
            summary = "login to the app and get a token",
            description = "Get a token access by sending a Login object to login ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = LogInDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400")
    })
    @PostMapping("/login")
    public HashMap<String, String> LogIn(@RequestBody LogInDto logInDto) {
        String token = authService.logIn(logInDto);
        HashMap<String, String> infoToken = new HashMap<>();
        infoToken.put("token", token);
        return infoToken;
    }

    @Operation(
            summary = "Get user info",
            description = "get information about user account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("/me")
    public UsersDto getMe() {
        return authService.getMyInfo();
    }

    @Operation(
            summary = "Sign up to the app and return a token",
            description = "Send a Register object (name, email, password) to sign up")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = RegisterDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400")
    })
    @PostMapping("/register")
    public HashMap<String, String> register(@RequestBody RegisterDto registerDto) {
        String token = authService.createUser(registerDto);
        HashMap<String, String> infoTokenReg = new HashMap<>();
        infoTokenReg.put("token", token);
        return infoTokenReg;
    }
}
