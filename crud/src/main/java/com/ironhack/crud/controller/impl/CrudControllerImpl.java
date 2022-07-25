package com.ironhack.crud.controller.impl;

import com.ironhack.crud.clients.OrderServiceClient;
import com.ironhack.crud.clients.PrimersServiceClient;
import com.ironhack.crud.clients.UserServiceClient;
import com.ironhack.crud.controller.interfaces.CrudController;
import com.ironhack.crud.models.Order;
import com.ironhack.crud.models.Primers;
import com.ironhack.crud.service.interfaces.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CrudControllerImpl implements CrudController {

    @Autowired
    private CrudService crudService;

    // Generate an order, calculating the price

    @PostMapping("/crud/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createAnOrderFromInterface(@PathVariable Integer userId,
                                            @RequestParam Optional<Integer> optionalPrimerId,
                                            @RequestParam Optional<Integer> optionalConcentration,
                                            @RequestBody Primers primersWithoutId){
        return crudService.createAnOrderFromInterface( userId, optionalPrimerId,
                optionalConcentration, primersWithoutId );
    }
}
