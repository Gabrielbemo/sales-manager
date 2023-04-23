package com.gabriel.store.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO;
import com.gabriel.store.services.SellerService;

@RestController
@RequestMapping("api/v1/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping()
    public ResponseEntity<List<ListSellerResponseDTO>> list(@Valid @NotNull @RequestParam final LocalDate startDate,@Valid @NotNull @RequestParam final LocalDate endDate){
        if(startDate.isAfter(endDate)){
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(
                sellerService.list(startDate, endDate),
                HttpStatus.OK
        );
    }
}
