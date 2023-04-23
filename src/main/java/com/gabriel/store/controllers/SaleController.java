package com.gabriel.store.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.gabriel.store.controllers.dtos.in.CreateSaleRequestDTO;
import com.gabriel.store.controllers.dtos.out.CreateSaleResponseDTO;
import com.gabriel.store.models.Sale;
import com.gabriel.store.services.SaleService;

@RestController
@RequestMapping("api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping()
    public ResponseEntity<CreateSaleResponseDTO> create(@RequestBody @Valid final CreateSaleRequestDTO createSaleRequestDTO) {
        Sale sale = saleService.create(createSaleRequestDTO.toEntity());
        return new ResponseEntity<>(
                CreateSaleResponseDTO.fromEntity(sale),
                HttpStatus.CREATED
        );
    }
}
