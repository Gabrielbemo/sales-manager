package com.gabriel.store.controllers;

import com.gabriel.store.controllers.dtos.in.CreateSaleRequestDTO;
import com.gabriel.store.controllers.dtos.out.CreateSaleResponseDTO;
import com.gabriel.store.models.Sale;
import com.gabriel.store.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/sales")
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping()
    public ResponseEntity<CreateSaleResponseDTO> create(@RequestBody @Valid CreateSaleRequestDTO createSaleRequestDTO) {
        Sale sale = saleService.create(createSaleRequestDTO.toEntity());
        return new ResponseEntity<>(
                CreateSaleResponseDTO.fromEntity(sale),
                HttpStatus.CREATED
        );
    }
}