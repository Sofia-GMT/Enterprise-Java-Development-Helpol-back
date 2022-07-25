package com.ironhack.crud.clients;

import com.ironhack.crud.controller.dto.StatusOrderDto;
import com.ironhack.crud.models.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("order-service")
public interface OrderServiceClient {

    @GetMapping("/orders")
    List<Order> findAll();

    @GetMapping("/orders/{id}")
    Order findById( @PathVariable Integer id);

    @PostMapping("/orders")
    Order store(@RequestBody Order order);

    @DeleteMapping("/orders/{id}")
    void delete(  @PathVariable Integer id);

    @PatchMapping("/orders/{id}/status")
    void updateStatusOrder(@PathVariable Integer id, @RequestBody StatusOrderDto status);







}
