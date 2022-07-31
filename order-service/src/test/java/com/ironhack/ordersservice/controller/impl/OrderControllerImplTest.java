package com.ironhack.ordersservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.ordersservice.controller.dto.StatusOrderDto;
import com.ironhack.ordersservice.enums.StatusOrder;
import com.ironhack.ordersservice.model.Order;
import com.ironhack.ordersservice.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerImplTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    Order order1, order2;

    @BeforeEach
    void setUp() {
        order1 = new Order( 1, 1, new BigDecimal( 25 ) );
        order2 = new Order( 2, 3, new BigDecimal( 30 ) );
        orderRepository.saveAll( List.of( order1, order2 ) );
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform( get( "/orders" ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "25" ) );
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "30" ) );
    }

    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform( get( "/orders/" + order1.getId() ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "25" ) );
        assertFalse( mvcResult.getResponse().getContentAsString().contains( "30" ) );
    }

    @Test
    void save() throws Exception {
        Order order3 = new Order( 5, 2, new BigDecimal( 28 ) );
        String body = objectMapper.writeValueAsString( order3 );

        MvcResult mvcResult = mockMvc.perform(
                        post( "/orders" )
                                .content( body )
                                .contentType( MediaType.APPLICATION_JSON )
                )
                .andExpect( status().isCreated() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();

        assertTrue( mvcResult.getResponse().getContentAsString().contains( "5" ) );
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "28" ) );
    }

    @Test
    void remove() throws Exception {
        MvcResult mvcResult = mockMvc.perform( delete( "/orders/" + order1.getId() ) )
                .andExpect( status().isNoContent() )
                .andReturn();
        assertFalse( orderRepository.existsById( order1.getId() ) );
    }

    @Test
    void updateNewStatus() throws Exception {

        StatusOrderDto statusOrderDto = new StatusOrderDto();
        statusOrderDto.setStatus( "DELIVERED" );

        String body = objectMapper.writeValueAsString( statusOrderDto );

        MvcResult mvcResult = mockMvc.perform(
                        patch( "/orders/" + order1.getId() +"/status" )
                                .content( body )
                                .contentType( MediaType.APPLICATION_JSON )
                )
                .andExpect( status().isNoContent() )
                .andReturn();

        assertEquals( StatusOrder.DELIVERED, order1.getStatus());
    }

}