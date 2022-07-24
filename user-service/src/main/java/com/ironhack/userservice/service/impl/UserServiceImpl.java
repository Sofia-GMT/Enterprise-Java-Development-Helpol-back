package com.ironhack.userservice.service.impl;

import com.ironhack.userservice.enums.Status;
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
    public void updateStatus(Integer id, String status) {
        // We check if the user exists
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found");
        }

        // We check if the new status is different
        Status newStatus = Status.valueOf( status.toUpperCase() );
        if (newStatus == optionalUser.get().getStatus()){
            throw new ResponseStatusException( HttpStatus.UNPROCESSABLE_ENTITY, "The user already has that status");
        }

        optionalUser.get().setStatus( newStatus );
        userRepository.save( optionalUser.get() );

    }
}
