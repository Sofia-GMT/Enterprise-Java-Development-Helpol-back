package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.controller.dto.StatusDto;
import com.ironhack.userservice.enums.StatusUser;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    User user1, user2;

    @BeforeEach
    void setUp() {
        user1 = new User( "Daniel González", 236, "000" );
        user2 = new User( "Andrea Jiménez", 562, "125" );
        userRepository.saveAll( List.of(user1, user2) );
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform( get( "/users" ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "236" ) );
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "562" ) );
    }

    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform( get( "/users/" + user1.getId() ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "236" ) );
        assertFalse( mvcResult.getResponse().getContentAsString().contains( "562" ) );
    }

    @Test
    void getStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform( get( "/users-status/" + user1.getId() ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();
        assertTrue( mvcResult.getResponse().getContentAsString().contains("PENDING") );
    }

    @Test
    void store() throws Exception {
        User user3 = new User( "Laura Pérez", 562, "1234" );
        String body = objectMapper.writeValueAsString( user3 );

        MvcResult mvcResult = mockMvc.perform(
                        post( "/users" )
                                .content( body )
                                .contentType( MediaType.APPLICATION_JSON )
                )
                .andExpect( status().isCreated() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();

        assertTrue( mvcResult.getResponse().getContentAsString().contains("Laura") );
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "562" ) );
    }

    @Test
    void remove() throws Exception {
        MvcResult mvcResult = mockMvc.perform( delete("/users/"+user1.getId()) )
                .andExpect( status().isNoContent() )
                .andReturn();
        assertFalse( userRepository.existsById( user1.getId() ) );
    }

    @Test
    void differentStatus() throws Exception{
        StatusDto statusDto = new StatusDto();
        statusDto.setStatus( "VALIDATED" );

        String body = objectMapper.writeValueAsString( statusDto );

        MvcResult mvcResult = mockMvc.perform(
                        patch( "/orders/" + user1.getId() +"/status" )
                                .content( body )
                                .contentType( MediaType.APPLICATION_JSON )
                )
                .andExpect( status().isNoContent() )
                .andReturn();

        System.out.println("Id user 1: "+user1.getId());
        assertEquals( StatusUser.VALIDATED, user1.getStatus());
    }
}