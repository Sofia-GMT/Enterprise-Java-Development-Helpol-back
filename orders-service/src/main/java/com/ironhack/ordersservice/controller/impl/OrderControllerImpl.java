package com.ironhack.ordersservice.controller.impl;

import com.ironhack.ordersservice.controller.dto.StatusDto;
import com.ironhack.ordersservice.controller.interfaces.OrderController;
import com.ironhack.ordersservice.enums.StatusOrder;
import com.ironhack.ordersservice.model.Order;
import com.ironhack.ordersservice.repository.OrderRepository;
import com.ironhack.ordersservice.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders") // Find all orders
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}") // Find order by Id
    @ResponseStatus(HttpStatus.OK)
    public Order findById(@PathVariable Integer id) {
        return orderService.findById(id);
    }


    @PostMapping("/orders") // Create new orders
    @ResponseStatus(HttpStatus.CREATED)
    public Order store(@RequestBody Order order) {
        order.setStatus( StatusOrder.PROCESSED );
        return orderRepository.save(order);
    }

    @DeleteMapping("/orders/{id}") // Delete an order
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        orderService.delete(id);
    }

    @PatchMapping("/orders/{id}/status") // Update status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
    @RequestBody StatusDto status) {
        orderService.updateStatus(id, status) ;
    }
}
