package com.ironhack.primersservice.controller.interfaces;

import com.ironhack.primersservice.models.Primers;


import java.util.List;

public interface PrimersController {
    List<Primers> findAll();
    Primers findById(Integer id);
    Primers store(Primers primers);
}
