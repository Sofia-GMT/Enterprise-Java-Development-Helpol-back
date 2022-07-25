package com.ironhack.crud.clients;

import com.ironhack.crud.controller.dto.StatusDto;
import com.ironhack.crud.models.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("order-service")
public interface OrderServiceClient {

    @PatchMapping("/orders/{id}/status")
    void updateStatus(Integer id, StatusDto status);

    @PostMapping("/orders")
    Order store(Order order);

    @DeleteMapping("/orders/{id}")
    void delete(Integer id);

    @GetMapping("/orders/{id}")
    Order findById(Integer id);

    @GetMapping("/orders")
    List<Order> findAll();
}
