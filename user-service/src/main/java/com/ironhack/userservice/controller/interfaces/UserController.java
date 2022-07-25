package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.controller.dto.StatusDto;
import com.ironhack.userservice.model.User;
import java.util.List;

public interface UserController {
    List<User> findAll();
    User findById( Integer id);
    StatusDto getStatus(Integer id);
    User store( User user);
    void delete(Integer id);
    void updateStatus(Integer id, StatusDto status);
}
