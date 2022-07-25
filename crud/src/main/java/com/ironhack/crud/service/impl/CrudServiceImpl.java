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
    @CircuitBreaker(name = "createAnOrderFromInterface", fallbackMethod = "createAnOrderFromInterfaceFallback")
    public Order createAnOrderFromInterface(Integer userId,
                                            Optional<Integer> optionalPrimerId,
                                            Optional<Integer> optionalConcentration,
                                            Primers primersWithoutId) {


        // WE CHECK IF THE USER IS REGISTERED

        try {
            User user = userServiceClient.findById( userId );
        } catch (
                ResponseStatusException e) {
            logger.error(e.getMessage());
        } // If the user is in our database then we can continue with the order

        Order order = null; // this is what we will return later

        Primers primers;

        // WE CHECK IF THE PRIMERS ARE STORED IN OUR DATABASE

        if (optionalPrimerId.isPresent()) {
            // if the primers are already in the database, we retrieve them with findById
            primers = primersServiceClient.findById( optionalPrimerId.get() );
        } else {
            // otherwise, we store them in the database
            primers = primersServiceClient.store( primersWithoutId );
        }

        // WE CALCULATE PRICE

        BigDecimal price = calculatePrice( optionalConcentration, primers );

        // WE CREATE AN ORDER
        order = orderServiceClient.store(
                    new Order( userId, primers.getId(), price ) );
        return order;
    }

    public Order createAnOrderFromInterfaceFallback(Exception e) {
        logger.error( e.getMessage() );
        return null;
    }

    public BigDecimal calculatePrice(Optional<Integer> optionalConcentration, Primers primers){

        BigDecimal price; // this is what we are going to return

        if (optionalConcentration.isPresent()) { // if the concentration is present

            BigDecimal pricePerNucleotide = null;

            switch (optionalConcentration.get()) {
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

            price = new BigDecimal(
                    primers.getForwardSequence().length()
                            + primers.getReverseSequence().length() )
                    .multiply( pricePerNucleotide );

        } else { // if the user did not indicate a concentration,
            // the default concentration is 25nmol
            price = new BigDecimal(
                    primers.getForwardSequence().length()
                            + primers.getReverseSequence().length() )
                    .multiply( BigDecimal.valueOf( 0.53 ) );
        }

        return price.setScale( 2, RoundingMode.HALF_UP );
    }


}
