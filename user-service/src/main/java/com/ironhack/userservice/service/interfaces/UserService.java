package com.ironhack.userservice.service.interfaces;

import com.ironhack.userservice.controller.dto.StatusDto;
import com.ironhack.userservice.model.User;

public interface UserService {
    User findById(Integer id);

    void delete(Integer id);

    void updateStatus(Integer id, StatusDto status);

    StatusDto getStatusDto(Integer id);
}
