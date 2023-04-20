package com.gabriel.store.controllers.dtos.in;

import com.gabriel.store.models.Sale;
import com.gabriel.store.models.Seller;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSaleRequestDTO {

    @NotNull(message = "The sale date cannot be null")
    private LocalDate saleDate;

    @NotNull(message = "The sale value cannot be null")
    private Long saleValue;

    @NotNull(message = "The seller cannot be null")
    private Long sellerId;

    public Sale toEntity() {
        return Sale.builder()
                .saleDate(saleDate)
                .saleValue(saleValue)
                .seller(new Seller(sellerId))
                .build();
    }
}
