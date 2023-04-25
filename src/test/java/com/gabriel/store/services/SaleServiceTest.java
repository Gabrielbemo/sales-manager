package com.gabriel.store.services;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import com.gabriel.store.models.Seller;
import com.gabriel.store.models.Sale;
import com.gabriel.store.repositories.SaleRepository;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {

    @InjectMocks
    private SaleService saleService;

    @Mock
    private SellerService sellerService;

    @Mock
    private SaleRepository saleRepository;

    private Sale testSale;

    @BeforeEach
    public void setup() {
        testSale = Sale.builder()
                .saleDate(LocalDate.now())
                .saleValue(123L)
                .seller(new Seller(1L))
                .build();
    }

    @Test
    public void createSale_WithValidData_ReturnsSale() {
        when(saleRepository.save(isA(Sale.class))).thenReturn(testSale);
        doNothing().when(sellerService).updateSellerSalesAmount(isA(Long.class));

        Sale sale = saleService.create(testSale);

        Assertions.assertEquals(testSale, sale);
    }

    @Test
    public void createSale_WithInvalidData_ReturnsException() {
        when(saleRepository.save(isA(Sale.class))).thenThrow(ConstraintViolationException.class);

        Assertions.assertThrows(ConstraintViolationException.class, () -> saleService.create(testSale));
    }
}
