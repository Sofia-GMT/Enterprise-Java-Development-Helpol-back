package com.ironhack.crud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.crud.clients.OrderServiceClient;
import com.ironhack.crud.clients.PrimersServiceClient;
import com.ironhack.crud.clients.UserServiceClient;
import com.ironhack.crud.models.Order;
import com.ironhack.crud.models.Primers;
import com.ironhack.crud.models.User;
import com.ironhack.crud.service.interfaces.CrudService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class CrudServiceImplTest {

    Primers primers1, mockPrimer;

    User mockUser;

    Order mockOrder;


    @Autowired
    private CrudServiceImpl crudService;

    @MockBean
    private OrderServiceClient orderServiceClient;
    @MockBean
    private PrimersServiceClient primersServiceClient;

    @MockBean
    private UserServiceClient userServiceClient;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        crudService = new CrudServiceImpl();
        primers1=new Primers("GATA3", "AAAATTTTTTTTTCG", "TCCCCCCCCGAA");
        mockPrimer = new Primers("MYC", "AAAAAAACCCCCCCCGTGTGT","CCCCCCCCGGGGGTA");
        mockOrder = new Order(254, 235,25);
        mockUser= new User("userName", 99, "password");
    }

    @AfterEach
    void tearDown() {
    }

    // CHECK IF THE CALLS ARE CORRECTLY DONE WITH THE METHOD findAll()

    @Test
    void findAllPrimers() {
        Mockito.when( primersServiceClient.findAll() ).thenReturn( List.of(mockPrimer));
        List<Primers> primersList = primersServiceClient.findAll();
        assertEquals(List.of(mockPrimer), primersServiceClient.findAll());
    }

    @Test
    void findAllUsers() {
        Mockito.when( userServiceClient.findAll() ).thenReturn( List.of(mockUser));
        List<User> primersList = userServiceClient.findAll();
        assertEquals(List.of(mockUser), userServiceClient.findAll());
    }

    @Test
    void findAllOrders() {
        Mockito.when( orderServiceClient.findAll() ).thenReturn( List.of(mockOrder));
        List<Order> primersList = orderServiceClient.findAll();
        assertEquals(List.of(mockOrder), orderServiceClient.findAll());
    }

    // CHECK IF THE PRICE IS CALCULATED CORRECTLY

    @Test
    void calculatePrice_WithConcentration25() {
        BigDecimal price = new BigDecimal( (15+12)*0.53)
                .setScale( 2, RoundingMode.HALF_UP ); // 14.31 USD
        assertEquals( price, crudService.calculatePrice( mockOrder ,primers1));

    }

    @Test
    void calculatePrice_WithConcentration100() {
        BigDecimal price = new BigDecimal( (15+12)*1.05)
                .setScale( 2, RoundingMode.HALF_UP ); // 28.35 USD
        mockOrder.setConcentration( 100 );
        assertEquals( price, crudService.calculatePrice(mockOrder ,primers1));
    }

    @Test
    void calculatePrice_WithConcentration250() {
        BigDecimal price = new BigDecimal( (15+12)*1.88)
                .setScale( 2, RoundingMode.HALF_UP ); // 50.76 USD
        mockOrder.setConcentration( 250 );
        assertEquals( price, crudService.calculatePrice(mockOrder ,primers1));

    }
}