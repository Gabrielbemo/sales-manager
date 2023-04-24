package com.gabriel.store.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SellerTest {

    private Validator validator;

    private Seller seller;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        seller = Seller.builder()
                .id(1L)
                .name("test1")
                .salesAmount(1)
                .build();
    }

    @Test
    public void validateSeller_WithValidData_ViolationsReturnsEmpty() {
        Set<ConstraintViolation<Seller>> violations = validator.validate(seller);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void validateSeller_WithInvalidData_ViolationsReturnsNotEmpty() {
        seller.setName(null);

        Set<ConstraintViolation<Seller>> violations = validator.validate(seller);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(violations.size(), 1);
    }
}
