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
        String token = authService.logIn(logInDto);
        HashMap<String, String> infoToken = new HashMap<>();
        infoToken.put("token", token);
        return infoToken;
    }

    @GetMapping("/me")
    public UsersDto getMe() {
        return authService.getMyInfo();
    }

    @PostMapping("/register")
    public HashMap<String, String> register(@RequestBody RegisterDto registerDto) {
        String token = authService.createUser(registerDto);
        HashMap<String, String> infoTokenReg = new HashMap<>();
        infoTokenReg.put("token", token);
        return infoTokenReg;
    }
}
