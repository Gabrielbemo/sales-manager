package com.gabriel.store.services;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO;
import com.gabriel.store.models.Seller;
import com.gabriel.store.repositories.SellerRepository;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {

    @InjectMocks
    private SellerService sellerService;

    @Mock
    private SellerRepository sellerRepository;

    List<ListSellerResponseDTO> listSellerResponseDTOS = new ArrayList<>();

    @BeforeEach
    public void setup() {
        listSellerResponseDTOS.add(ListSellerResponseDTO.builder()
                .name("test")
                .salesAmount(1)
                .salesDailyAverage(4L)
                .build());
    }

    @Test
    public void listSellers_WithValidData_ReturnsListSellerResponseDTO() {
        when(sellerRepository.findSellersByPeriod(isA(LocalDate.class), isA(LocalDate.class))).thenReturn(listSellerResponseDTOS);

        List<ListSellerResponseDTO> sellers = sellerService.list(LocalDate.now(), LocalDate.now());

        Assertions.assertEquals(sellers.get(0).getName(), listSellerResponseDTOS.get(0).getName());
        Assertions.assertEquals(sellers.get(0).getSalesAmount(), listSellerResponseDTOS.get(0).getSalesAmount());
        Assertions.assertEquals(sellers.get(0).getSalesDailyAverage(), listSellerResponseDTOS.get(0).getSalesDailyAverage());
    }

    @Test
    public void listSellers_WithInvalidData_ReturnsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> sellerService.list(null, LocalDate.now()));
    }

    @Test
    public void calculateSalesDailyAverage_WithValidData_ReturnsListSellerResponseDTO() {
        List<ListSellerResponseDTO> sellers = sellerService.calculateSalesDailyAverage(listSellerResponseDTOS, 2);
        Assertions.assertEquals(sellers.get(0).getName(), listSellerResponseDTOS.get(0).getName());
        Assertions.assertEquals(sellers.get(0).getSalesAmount(), listSellerResponseDTOS.get(0).getSalesAmount());
        Assertions.assertEquals(sellers.get(0).getSalesDailyAverage(), 2);
    }

    @Test
    public void calculateSalesDailyAverage_WithInvalidData_ReturnsListSellerResponseDTO() {
        Assertions.assertThrows(NullPointerException.class, () -> sellerService.calculateSalesDailyAverage(null, 2));
    }

    @Test
    public void updateSellerSalesAmount_WithValidData_DontReturnsException() {
        when(sellerRepository.findById(isA(Long.class))).thenReturn(Optional.ofNullable(Seller.builder().id(1L).salesAmount(1).build()));
        Assertions.assertDoesNotThrow(() -> sellerService.updateSellerSalesAmount(1L));
    }
}
