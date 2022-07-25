package com.ironhack.ordersservice.services.interfaces;

import com.ironhack.ordersservice.controller.dto.StatusDto;
import com.ironhack.ordersservice.model.Order;

public interface OrderService {
    Order findById(Integer id);

    void delete(Integer id);

    void updateStatus(Integer id, StatusDto status);
}
