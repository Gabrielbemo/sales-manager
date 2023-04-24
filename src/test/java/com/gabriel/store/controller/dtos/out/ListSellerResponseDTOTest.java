package com.gabriel.store.controller.dtos.out;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO;

public class ListSellerResponseDTOTest {

    private Validator validator;

    ListSellerResponseDTO listSellerResponseDTO;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        listSellerResponseDTO = ListSellerResponseDTO.builder()
                .name("test")
                .salesAmount(1)
                .salesDailyAverage(1L)
                .build();
    }

    @Test
    public void validateDTO_WithValidData_ViolationsReturnsEmpty() {
        Set<ConstraintViolation<ListSellerResponseDTO>> violations = validator.validate(listSellerResponseDTO);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void validateDTO_WithInvalidData_ViolationsReturnsNotEmpty() {
        listSellerResponseDTO.setName(null);
        listSellerResponseDTO.setSalesDailyAverage(null);

        Set<ConstraintViolation<ListSellerResponseDTO>> violations = validator.validate(listSellerResponseDTO);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(2, violations.size());
    }
}
