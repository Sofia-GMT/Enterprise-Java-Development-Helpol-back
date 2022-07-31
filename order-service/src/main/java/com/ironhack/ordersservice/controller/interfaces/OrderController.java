package com.ironhack.ordersservice.controller.interfaces;

import com.ironhack.ordersservice.controller.dto.StatusOrderDto;
import com.ironhack.ordersservice.model.Order;


import java.util.List;

public interface OrderController {
    void updateStatusOrder(Integer id, StatusOrderDto status);
    Order store(Order order);
    void delete(Integer id);
    Order findById(Integer id);
    List<Order> findAll();
}
