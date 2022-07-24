package com.ironhack.userservice.service.interfaces;

import com.ironhack.userservice.model.User;

public interface UserService {
    User findById(Integer id);

    void delete(Integer id);

    void updateStatus(Integer id, String status);
}
