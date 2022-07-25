package com.ironhack.crud.service.impl;

import com.ironhack.crud.models.Primers;
import com.ironhack.crud.service.interfaces.CrudService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CrudServiceImplTest {

    Primers primers1;

    private CrudServiceImpl crudService;

    @BeforeEach
    void setUp() {
        crudService = new CrudServiceImpl();
        primers1=new Primers("GATA3", "AAAATTTTTTTTTCG", "TCCCCCCCCGAA");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculatePrice_WithConcentration25() {
        BigDecimal price = new BigDecimal( (15+12)*0.53)
                .setScale( 2, RoundingMode.HALF_UP ); // 14.31 USD
        assertEquals( price, crudService.calculatePrice(Optional.of( 25 ),primers1));

    }

    @Test
    void calculatePrice_WithConcentration100() {
        BigDecimal price = new BigDecimal( (15+12)*1.05)
                .setScale( 2, RoundingMode.HALF_UP ); // 28.35 USD
        assertEquals( price, crudService.calculatePrice(Optional.of( 100 ),primers1));

    }

    @Test
    void calculatePrice_WithConcentration250() {
        BigDecimal price = new BigDecimal( (15+12)*1.88)
                .setScale( 2, RoundingMode.HALF_UP ); // 50.76 USD
        assertEquals( price, crudService.calculatePrice(Optional.of( 250 ),primers1));

    }

    @Test
    void calculatePrice_WithoutConcentration() {
        BigDecimal price = new BigDecimal( (15+12)*0.53)
                .setScale( 2, RoundingMode.HALF_UP ); // 14.31 USD
        assertEquals( price, crudService.calculatePrice( Optional.empty(),primers1));
    }
}