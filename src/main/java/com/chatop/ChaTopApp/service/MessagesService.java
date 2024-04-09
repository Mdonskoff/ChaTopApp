package com.chatop.ChaTopApp.service;

import com.chatop.ChaTopApp.dto.MessagesDto;
import com.chatop.ChaTopApp.model.Messages;
import com.chatop.ChaTopApp.model.Rentals;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.repository.MessagesRepository;
import com.chatop.ChaTopApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private RentalsService rentalsService;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public Messages saveMessage(MessagesDto messagesDto) {
        Messages messages = new Messages();
        messages.setMessage(messagesDto.getMessage());
        Rentals rental = rentalsService.getARental(messagesDto.getRental_id());
        Users user = usersRepository.findById(messagesDto.getUser_id()).get();
        messages.setUser(user);
        messages.setRental(rental);
        return messagesRepository.save(messages);
    }


}
