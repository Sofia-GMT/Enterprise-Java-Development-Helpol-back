package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.dto.StatusDto;
import com.ironhack.userservice.controller.interfaces.UserController;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import com.ironhack.userservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users") // Find all users
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}") // Find user by Id
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }


    // Check status of the user. Only the validated ones can order primers
    @GetMapping("/users-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StatusDto getStatus(@PathVariable Integer id) {
        return userService.getStatusDto(id);

    }

    @PostMapping("/users") // Create new users
    @ResponseStatus(HttpStatus.CREATED)
    public User store(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}") // Delete a user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PatchMapping("/users/{id}/status") // Update status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody StatusDto status) {
        userService.updateStatus(id, status);
    }

}
