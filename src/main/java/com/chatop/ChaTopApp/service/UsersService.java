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

    public UsersDto saveUser(Users user) {
        user.setId(-1);
        user = usersRepository.save(user);
        return new UsersDto(user.getId(), user.getName(), user.getEmail(), user.getCreated_at(), user.getUpdated_at());
    }

    public UsersDto getAUser(int idUser) {
        try {
            Users user = usersRepository.findById(idUser).get();
            return new UsersDto(user.getId(), user.getName(), user.getEmail(), user.getCreated_at(), user.getUpdated_at());

        } catch(Exception e) {
            log.error(e.getMessage()+ "Erreur dans getUser");
            return null;
        }
    }

}
