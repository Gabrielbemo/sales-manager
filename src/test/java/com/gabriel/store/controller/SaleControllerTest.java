package com.gabriel.store.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.store.controllers.SaleController;
import com.gabriel.store.controllers.dtos.in.CreateSaleRequestDTO;
import com.gabriel.store.controllers.dtos.out.CreateSaleResponseDTO;
import com.gabriel.store.models.Sale;
import com.gabriel.store.services.SaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

@WebMvcTest(SaleController.class)
public class SaleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SaleService saleService;

    @Autowired
    ObjectMapper objectMapper;

    static CreateSaleRequestDTO createSaleRequestDTO;

    @BeforeAll
    public static void setup() {
        createSaleRequestDTO = CreateSaleRequestDTO.builder()
                .saleDate(LocalDate.now())
                .saleValue(123L)
                .sellerId(1L)
                .build();
    }

    @Test
    public void createSale_WithValidData_returnsCreated() throws Exception {
        when(saleService.create(isA(Sale.class)))
                .thenReturn(createSaleRequestDTO.toEntity());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSaleRequestDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        CreateSaleResponseDTO createSaleResponseDTO = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                CreateSaleResponseDTO.class
        );

        Assertions.assertEquals(createSaleResponseDTO.getSaleDate(), createSaleRequestDTO.getSaleDate());
    }

    @Test
    public void createSale_WithInvalidData_ReturnsBadRequest() throws Exception {
        createSaleRequestDTO.setSaleDate(null);
        createSaleRequestDTO.setSaleValue(null);
        createSaleRequestDTO.setSellerId(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSaleRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
