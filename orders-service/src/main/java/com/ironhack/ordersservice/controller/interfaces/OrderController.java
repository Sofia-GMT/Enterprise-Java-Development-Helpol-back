package com.ironhack.ordersservice.controller.interfaces;

import com.ironhack.ordersservice.controller.dto.StatusDto;
import com.ironhack.ordersservice.model.Order;


import java.util.List;

public interface OrderController {
    void updateStatus(Integer id, StatusDto status);
    Order store(Order order);
    void delete(Integer id);
    Order findById(Integer id);
    List<Order> findAll();
}
