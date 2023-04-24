package com.gabriel.store.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO;
import com.gabriel.store.models.Sale;
import com.gabriel.store.models.Seller;

@DataJpaTest
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findSellers_WithValidData_ReturnsSale() {
        Seller seller = testEntityManager.persistFlushFind(Seller.builder().name("test").build());
        testEntityManager.persistFlushFind(Sale.builder().saleDate(LocalDate.parse("1900-10-21")).saleValue(1L).seller(seller).build());

        List<ListSellerResponseDTO> sellers = sellerRepository.findSellersByPeriod(LocalDate.parse("1900-10-21"), LocalDate.parse("1900-10-21"));
        ListSellerResponseDTO sellerTest = sellers.stream().filter(foundSeller -> Objects.equals(foundSeller.getName(), seller.getName())).toList().stream().findFirst().get();
        Assertions.assertNotNull(sellers);
        Assertions.assertEquals(sellerTest.getName(), seller.getName());
        Assertions.assertEquals(sellerTest.getSalesAmount(), seller.getSalesAmount());
        Assertions.assertEquals(sellerTest.getSalesDailyAverage(), 1);
    }
}
