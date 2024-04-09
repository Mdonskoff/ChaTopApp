package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.*;
import com.chatop.ChaTopApp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(
            summary = "login to the app and get a token",
            description = "Get a token access by sending a Login object to login ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = LogInDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenDto> LogIn(@RequestBody LogInDto logInDto) {
        return ResponseEntity.ok(new TokenDto(authService.logIn(logInDto)));
    }

    @Operation(
            summary = "Get user info",
            description = "get information about user account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("/me")
    public ResponseEntity<UsersDto> getMe() {
        return ResponseEntity.ok(authService.getMyInfo());
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
    public ResponseEntity<TokenDto> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(new TokenDto(authService.createUser(registerDto)));
    }
}
