package com.chatop.ChaTopApp.service;

import com.chatop.ChaTopApp.dto.LogInDto;
import com.chatop.ChaTopApp.dto.RegisterDto;
import com.chatop.ChaTopApp.dto.UsersDto;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createUser(RegisterDto registerDto) {
        if(registerDto.getEmail() == null || registerDto.getPassword() == null || registerDto.getName() == null) {
            log.error("Check users informations");
            return "";
        }

        Optional<Users> checkUser = usersRepository.findByEmail(registerDto.getEmail());
        if(checkUser.isPresent()) {
            log.error("User already exist");
            return "";
        }
        Users user = new Users();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        usersRepository.save(user);

        LogInDto logInDto = new LogInDto(registerDto.getEmail(), registerDto.getPassword());
        return logIn(logInDto);
    }

    public String logIn(LogInDto logInDto) {
        if(logInDto.getEmail() == null || logInDto.getPassword() == null) {
            return "login/password incorrect";
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDto.getEmail(),
                            logInDto.getPassword()
                    )
            );
            Users user = usersRepository.findByEmail(logInDto.getEmail())
                    .orElseThrow();
           return jwtService.generateToken(user);
        }catch(Exception ex) {
            log.error("erreur de connexion " + ex);
            return "";
        }

    }
    public UsersDto getMyInfo(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = usersRepository.findByEmail(email).orElseThrow();
        UsersDto usersDto = new UsersDto();
        usersDto.setId(user.getId());
        usersDto.setName(user.getName());
        usersDto.setEmail(user.getEmail());
        usersDto.setCreated_at(user.getCreated_at());
        usersDto.setUpdated_at(user.getUpdated_at());

        return usersDto;
    }

}
