package com.ironhack.crud.clients;

import com.ironhack.crud.models.Primers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("primers-service")
public interface PrimersServiceClient {


    @GetMapping("/primers")
    List<Primers> findAll();

    @GetMapping("/primers/{id}")
    Primers findById(Integer id);

    @PostMapping("/primers")
    Primers store(Primers primers);
}
