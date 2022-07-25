package com.ironhack.crud.clients;

import com.ironhack.crud.controller.dto.StatusUserDto;
import com.ironhack.crud.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user-service")
public interface UserServiceClient {

    @GetMapping("/users")
    List<User> findAll();

    @GetMapping("/users/{id}")
    User findById( @PathVariable Integer id);

    @GetMapping("/users-status/{id}")
    StatusUserDto getStatus(@PathVariable Integer id);

    @PostMapping("/users")
    User store(@RequestBody User user);

    @DeleteMapping("/users/{id}")
    void delete(@PathVariable Integer id);

    @PatchMapping("/users/{id}/status")
    void updateStatusUser(@PathVariable Integer id, @RequestBody StatusUserDto status);
}
