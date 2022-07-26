package com.ironhack.userservice.service.impl;

import com.ironhack.userservice.controller.dto.StatusDto;
import com.ironhack.userservice.enums.StatusUser;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import com.ironhack.userservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    public User findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found");
        }
        return optionalUser.get();
    }

    @Override
    public void delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.delete( optionalUser.get() );
    }

    @Override
    public void updateStatus(Integer id, StatusDto status) {
        // We check if the user exists
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found");
        }

        // We check if the new status is different
        StatusUser newStatusUser = StatusUser.valueOf( status.getStatus().toUpperCase() );
        if (newStatusUser == optionalUser.get().getStatus()){
            throw new ResponseStatusException( HttpStatus.UNPROCESSABLE_ENTITY, "The user already has that status");
        }

        optionalUser.get().setStatus( newStatusUser );
        userRepository.save( optionalUser.get() );

    }

    @Override
    public StatusDto getStatusDto(Integer id) {
        StatusDto statusDto = new StatusDto();
        statusDto.setStatus( userRepository.findById(id).get().getStatus().toString());
        return statusDto;
    }
}
