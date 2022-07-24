package com.ironhack.ordersservice.services.impl;

import com.ironhack.ordersservice.enums.Status;
import com.ironhack.ordersservice.model.Order;
import com.ironhack.ordersservice.repository.OrderRepository;
import com.ironhack.ordersservice.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findById(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Order not found");
        }
        return optionalOrder.get();
    }

    @Override
    public void delete(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.delete( optionalOrder.get() );
    }


    public void updateStatus(Integer id, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Order not found");
        }

        // We check if the new status is different

        Status newStatus = null;
        try {
            newStatus = Status.valueOf( status.toUpperCase() );
        } catch (IllegalArgumentException e){
            newStatus = optionalOrder.get().getStatus();
            // If the string provided cannot be assigned to an Status,
            // the initial status remains the same
        }

        if (newStatus == optionalOrder.get().getStatus()){
            throw new ResponseStatusException( HttpStatus.UNPROCESSABLE_ENTITY,
                    "The status is "+optionalOrder.get().getStatus());
        }

        optionalOrder.get().setStatus( newStatus );
        orderRepository.save( optionalOrder.get() );

    }
}
