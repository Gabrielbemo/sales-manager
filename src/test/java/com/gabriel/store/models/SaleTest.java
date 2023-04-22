package com.gabriel.store.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

public class SaleTest {

    private Validator validator;

    private Sale sale;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        sale = Sale.builder()
                .id(1L)
                .saleDate(LocalDate.now())
                .saleValue(123L)
                .seller(new Seller(1L))
                .build();
    }

    @Test
    public void validateSale_WithValidData_ViolationsReturnsEmpty() {
        Set<ConstraintViolation<Sale>> violations = validator.validate(sale);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void validateSale_WithInvalidData_ViolationsReturnsNotEmpty() {
        sale.setSaleDate(null);
        sale.setSaleValue(null);
        sale.setSeller(null);

        Set<ConstraintViolation<Sale>> violations = validator.validate(sale);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(violations.size(), 3);
    }
}
