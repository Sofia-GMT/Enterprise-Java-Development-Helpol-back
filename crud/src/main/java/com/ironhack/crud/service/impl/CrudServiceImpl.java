package com.ironhack.crud.service.impl;

import com.ironhack.crud.clients.OrderServiceClient;
import com.ironhack.crud.clients.PrimersServiceClient;
import com.ironhack.crud.clients.UserServiceClient;
import com.ironhack.crud.controller.impl.CrudControllerImpl;
import com.ironhack.crud.models.Order;
import com.ironhack.crud.models.Primers;
import com.ironhack.crud.models.User;
import com.ironhack.crud.service.interfaces.CrudService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CrudServiceImpl implements CrudService {

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private PrimersServiceClient primersServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    private final Logger logger = LoggerFactory.getLogger( CrudControllerImpl.class);

    @Override
    //@CircuitBreaker(name = "createAnOrderFromInterface", fallbackMethod = "createAnOrderFromInterfaceFallback")
    public Order createAnOrderFromInterface(Order orderWithoutPrice) {



        // WE CHECK IF THE USER AND THE PRIMERS ARE STORED IN THE SYSTEM

        Primers primers = null;
        User user = null;

        try {
            user = userServiceClient.findById( orderWithoutPrice.getUserId() );
            primers = primersServiceClient.findById( orderWithoutPrice.getPrimersId() );

        } catch (ResponseStatusException e) {
            logger.error(e.getMessage());
        }

        // WE CALCULATE PRICE

        BigDecimal price = calculatePrice( orderWithoutPrice, primers );

        // WE CREATE AN ORDER
        Order newOrder = orderServiceClient.store(
                    new Order( user.getId(), primers.getId(), price, orderWithoutPrice.getConcentration())
        );
        return newOrder;
    }




    public Order createAnOrderFromInterfaceFallback(Exception e) {
        logger.error( e.getMessage() );
        return new Order(); // returns an empty order
    }







    public BigDecimal calculatePrice(Order orderWithoutPrice, Primers primers){

        BigDecimal pricePerNucleotide = null;


        switch (orderWithoutPrice.getConcentration()) {
            case 25:
                pricePerNucleotide = new BigDecimal( 0.53 );
                break;
            case 100:
                pricePerNucleotide = new BigDecimal( 1.05 );
                break;
            case 250:
                pricePerNucleotide = new BigDecimal( 1.88 );
                break;
        }

        // we count the total number of nucleotides and then multiply by
        // the price per nucleotide

        BigDecimal price = new BigDecimal(
            primers.getForwardSequence().length()
                    + primers.getReverseSequence().length() )
            .multiply( pricePerNucleotide );

        return price.setScale( 2, RoundingMode.HALF_UP );
    }


}
