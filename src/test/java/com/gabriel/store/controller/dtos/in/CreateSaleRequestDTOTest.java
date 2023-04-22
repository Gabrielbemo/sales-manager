package com.gabriel.store.controller.dtos.in;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import com.gabriel.store.controllers.dtos.in.CreateSaleRequestDTO;
import com.gabriel.store.models.Sale;

public class CreateSaleRequestDTOTest {
    private Validator validator;

    CreateSaleRequestDTO createSaleRequestDTO;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        createSaleRequestDTO = CreateSaleRequestDTO.builder()
                .saleDate(LocalDate.now())
                .saleValue(123L)
                .sellerId(1L)
                .build();
    }

    @Test
    public void convertToEntity_WithValidData_ReturnsSale() {
        Sale sale = createSaleRequestDTO.toEntity();

        Assertions.assertEquals(sale.getSaleDate(), createSaleRequestDTO.getSaleDate());
        Assertions.assertEquals(sale.getSaleValue(), createSaleRequestDTO.getSaleValue());
        Assertions.assertEquals(sale.getSeller().getId(), createSaleRequestDTO.getSellerId());
    }

    @Test
    public void validateDTO_WithValidData_ViolationsReturnsEmpty() {
        Set<ConstraintViolation<CreateSaleRequestDTO>> violations = validator.validate(createSaleRequestDTO);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void validateDTO_WithInvalidData_ViolationsReturnsNotEmpty() {
        createSaleRequestDTO.setSaleDate(null);
        createSaleRequestDTO.setSaleValue(null);
        createSaleRequestDTO.setSellerId(null);

        Set<ConstraintViolation<CreateSaleRequestDTO>> violations = validator.validate(createSaleRequestDTO);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(violations.size(), 3);
    }
}
