package com.ironhack.primersservice.service.impl;

import com.ironhack.primersservice.models.Primers;
import com.ironhack.primersservice.repository.PrimersRepository;
import com.ironhack.primersservice.service.interfaces.PrimersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PrimersServiceImpl implements PrimersService {
    @Autowired
    private PrimersRepository primersRepository;

    @Override
    public Primers findById(Integer id) {
        Optional<Primers> optionalPrimers = primersRepository.findById(id);
        if (!optionalPrimers.isPresent()) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Primers not found");
        }
        return optionalPrimers.get();
    }
}
