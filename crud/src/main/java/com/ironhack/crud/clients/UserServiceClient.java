package com.ironhack.crud.clients;

import com.ironhack.crud.controller.dto.StatusDto;
import com.ironhack.crud.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("user-service")
public interface UserServiceClient {

    @GetMapping("/users")
    List<User> findAll();

    @GetMapping("/users/{id}")
    User findById( Integer id);

    @GetMapping("/users-status/{id}")
    StatusDto getStatus(Integer id);

    @PostMapping("/users")
    User store( User user);

    @DeleteMapping("/users/{id}")
    void delete(Integer id);

    @PatchMapping("/users/{id}/status")
    void updateStatus(Integer id, StatusDto status);
}
