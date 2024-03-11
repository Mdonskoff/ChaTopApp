package com.chatop.ChaTopApp.service;

import com.chatop.ChaTopApp.dto.RegisterDto;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(RegisterDto registerDto) {
        Users user = new Users();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setPassword(registerDto.getPassword());

        Users createdUSer = usersRepository.save(user);
        return createdUSer;
    }
}
