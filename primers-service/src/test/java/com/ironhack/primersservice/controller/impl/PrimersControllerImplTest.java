package com.ironhack.primersservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.primersservice.models.Primers;
import com.ironhack.primersservice.repository.PrimersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PrimersControllerImplTest {

    @Autowired
    private PrimersRepository primersRepository;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    Primers primers1, primers2;

    @BeforeEach
    void setUp() {
        primers1 = new Primers( "GATA3", "AACCCCCGGGGTTT", "AAGCCCCGGGGTTT" );
        primers2 = new Primers( "MYC", "AAAAAAAGGGGGGTTT", "TTTTCGGGGGG" );
        primersRepository.saveAll( List.of(primers1, primers2) );
    }

    @AfterEach
    void tearDown() {
        primersRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform( get( "/primers" ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "GATA3" ) );
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "MYC" ) );
    }

    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform( get( "/primers/" + primers1.getId() ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();
        assertTrue( mvcResult.getResponse().getContentAsString().contains( "GATA3" ) );
        assertFalse( mvcResult.getResponse().getContentAsString().contains( "MYC" ) );
    }

    @Test
    void create() throws Exception {
        Primers primers3 = new Primers( "OCT4", "GGGGGGGGTTTTTTTTTAAA", "ACGCTACACACACACAC" );
        String body = objectMapper.writeValueAsString( primers3 );

        MvcResult mvcResult = mockMvc.perform(
                        post( "/primers" )
                                .content( body )
                                .contentType( MediaType.APPLICATION_JSON )
                )
                .andExpect( status().isCreated() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andReturn();

        assertTrue( mvcResult.getResponse().getContentAsString().contains( "OCT4" ) );
    }
}