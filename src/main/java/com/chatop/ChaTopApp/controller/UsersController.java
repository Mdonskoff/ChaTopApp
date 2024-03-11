package com.chatop.ChaTopApp.controller;

import com.chatop.ChaTopApp.dto.UsersDto;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public Users createUser(@RequestBody Users user) {
        return usersService.saveUser(user);
    }

    @GetMapping("/{id}")
    public UsersDto getUser(@PathVariable int id) {
        return usersService.getAUser(id);
    }

    @GetMapping("")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

}
