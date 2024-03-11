package com.chatop.ChaTopApp.service;

import com.chatop.ChaTopApp.dto.UsersDto;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users saveUser(Users user) {
        user.setId(-1); //ça créera toujours un nouvel user
        Users savedUser = usersRepository.save(user);
        return savedUser;
    }

    public UsersDto getAUser(int idUser) {
        try {
            Users user;
            user = usersRepository.findById(idUser).get();
            UsersDto userDto = new UsersDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setCreated_at(user.getCreated_at());
            userDto.setUpdated_at(user.getUpdated_at());
            return userDto;

        } catch(Exception e) {
            log.error(e.getMessage()+ "Erreur dans getUser");
            return null;
        }
    }
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
