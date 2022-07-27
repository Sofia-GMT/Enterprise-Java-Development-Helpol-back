package com.ironhack.primersservice.controller.impl;

import com.ironhack.primersservice.controller.interfaces.PrimersController;
import com.ironhack.primersservice.models.Primers;
import com.ironhack.primersservice.repository.PrimersRepository;
import com.ironhack.primersservice.service.interfaces.PrimersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PrimersControllerImpl implements PrimersController {
    @Autowired
    private PrimersRepository primersRepository;
    @Autowired
    private PrimersService primersService;

    @GetMapping("/primers") // Find all primers
    @ResponseStatus(HttpStatus.OK)
    public List<Primers> findAll() {
        return primersRepository.findAll();
    }

    @GetMapping("/primers/{id}") // Find primers by Id
    @ResponseStatus(HttpStatus.OK)
    public Primers findById(@PathVariable Integer id) {
        return primersService.findById(id);
    }

    @PostMapping("/primers") // Store new primers
    @ResponseStatus(HttpStatus.CREATED)
    public Primers store(@RequestBody Primers primers) {
        return primersRepository.save(primers);
    }

}
