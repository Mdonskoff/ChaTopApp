package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.LogInDto;
import com.chatop.ChaTopApp.dto.RegisterDto;
import com.chatop.ChaTopApp.dto.UsersDto;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.service.AuthService;
import com.chatop.ChaTopApp.service.UsersService;
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

    @PostMapping("/login")
    public HashMap<String, String> LogIn(@RequestBody LogInDto logInDto) {
        HashMap<String, String> infoToken = new HashMap<>();
        infoToken.put("token", "jwt");
        return infoToken;
    }

    @GetMapping("/me")
    public UsersDto getMe() {
        return usersService.getAUser(1);
    }

    @PostMapping("/register")
    public HashMap<String, String> register(@RequestBody RegisterDto registerDto) {
        authService.createUser(registerDto);
        HashMap<String, String> infoTokenReg = new HashMap<>();
        infoTokenReg.put("token", "jwt");
        return infoTokenReg;
    }
}
