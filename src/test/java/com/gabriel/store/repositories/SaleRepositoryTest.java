package com.gabriel.store.repositories;

import com.gabriel.store.models.Sale;
import com.gabriel.store.models.Seller;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

@DataJpaTest
public class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Sale testSale;

    @BeforeEach
    public void setup() {
        testSale = Sale.builder()
                .saleDate(LocalDate.now())
                .saleValue(123L)
                .build();
    }

    @Test
    public void createSale_WithValidData_ReturnsSale(){
        Seller seller = testEntityManager.persistFlushFind(Seller.builder().name("test").build());
        testSale.setSeller(new Seller(seller.getId()));

        Sale savedSale = saleRepository.save(testSale);

        Sale findedSale = testEntityManager.find(Sale.class, savedSale.getId());

        Assertions.assertNotNull(findedSale);
        Assertions.assertEquals(findedSale.getSaleDate(), testSale.getSaleDate());
        Assertions.assertEquals(findedSale.getSaleValue(), testSale.getSaleValue());
        Assertions.assertEquals(findedSale.getSeller().getId(), testSale.getSeller().getId());
    }

    @Test
    public void createSale_WithInvalidData_ReturnsException(){
        testSale.setSaleDate(null);
        testSale.setSaleValue(null);
        testSale.setSeller(null);

        Assertions.assertThrows(ConstraintViolationException.class, () -> saleRepository.save(testSale));
    }
}
