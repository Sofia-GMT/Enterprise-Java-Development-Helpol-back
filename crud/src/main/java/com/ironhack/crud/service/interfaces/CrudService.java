package com.ironhack.crud.service.interfaces;

import com.ironhack.crud.models.Order;
import com.ironhack.crud.models.Primers;

import java.util.Optional;

public interface CrudService {


    Order createAnOrderFromInterface(Integer userId,
                                     Optional<Integer> optionalPrimerId,
                                     Optional<Integer> optionalConcentration);
}
