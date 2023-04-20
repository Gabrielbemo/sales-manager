package com.gabriel.store.controllers.dtos.out;

import com.gabriel.store.models.Sale;
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
public class CreateSaleResponseDTO {

    @NotNull(message = "The sale id cannot be null")
    private Long id;

    @NotNull(message = "The sale date cannot be null")
    private LocalDate saleDate;

    public static CreateSaleResponseDTO fromEntity(final Sale sale) {
        return CreateSaleResponseDTO.builder()
                .id(sale.getId())
                .saleDate(sale.getSaleDate())
                .build();
    }
}
