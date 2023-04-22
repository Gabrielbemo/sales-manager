package com.gabriel.store.controller.dtos.out;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import com.gabriel.store.controllers.dtos.out.CreateSaleResponseDTO;
import com.gabriel.store.models.Sale;

public class CreateSaleResponseDTOTest {
    private Validator validator;

    CreateSaleResponseDTO createSaleResponseDTO;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        createSaleResponseDTO = CreateSaleResponseDTO.builder()
                .id(1L)
                .saleDate(LocalDate.now())
                .build();
    }

    @Test
    public void convertFromEntity_WithValidData_ReturnsCreateSaleResponseDTO() {
        Sale sale = Sale.builder()
                .id(1L)
                .saleDate(LocalDate.now())
                .build();
        CreateSaleResponseDTO testedSaleResponseDTO = CreateSaleResponseDTO.fromEntity(sale);

        Assertions.assertEquals(sale.getSaleDate(), testedSaleResponseDTO.getSaleDate());
        Assertions.assertEquals(sale.getId(), testedSaleResponseDTO.getId());
    }

    @Test
    public void validateDTO_WithValidData_ViolationsReturnsEmpty() {
        Set<ConstraintViolation<CreateSaleResponseDTO>> violations = validator.validate(createSaleResponseDTO);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void validateDTO_WithInvalidData_ViolationsReturnsNotEmpty() {
        createSaleResponseDTO.setSaleDate(null);
        createSaleResponseDTO.setId(null);

        Set<ConstraintViolation<CreateSaleResponseDTO>> violations = validator.validate(createSaleResponseDTO);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(violations.size(), 2);
    }
}
