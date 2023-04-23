package com.gabriel.store.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.store.controllers.SellerController;
import com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO;
import com.gabriel.store.services.SellerService;

@WebMvcTest(SellerController.class)
public class SellerControllerTest {

    @MockBean
    private SellerService sellerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final List<ListSellerResponseDTO> sellersList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        sellersList.add(ListSellerResponseDTO.builder()
                .name("test1")
                .salesAmount(1L)
                .salesDailyAverage(1.0)
                .build());

        sellersList.add(ListSellerResponseDTO.builder()
                .name("test2")
                .salesAmount(2L)
                .salesDailyAverage(2.0)
                .build());
    }

    @Test
    public void getSellers_WithValidData_returnsOk() throws Exception {
        when(sellerService.list(isA(LocalDate.class), isA(LocalDate.class)))
                .thenReturn(sellersList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/sellers?startDate=2023-10-25&endDate=2023-10-25"))
                .andExpect(status().isOk())
                .andReturn();

        List<ListSellerResponseDTO> listSellerResponseDTOS = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                List.class
        );

        Assertions.assertEquals(listSellerResponseDTOS.size(), 2);
    }

    @Test
    public void getSellers_WithInvalidRequestParam_returnsBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/sellers?startDate=2023-10-28&endDate=2023-10-25"))
                .andExpect(status().isBadRequest())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/sellers?startDate=null&endDate=2023-10-25"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
