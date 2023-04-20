package com.gabriel.store.controllers.dtos.out;

import com.gabriel.store.models.Sale;
import com.gabriel.store.models.Seller;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSaleResponseDTO {

    @NotNull(message = "The sale id cannot be null")
    private Long id;

    public static CreateSaleResponseDTO fromEntity(final Sale sale) {
        return CreateSaleResponseDTO.builder()
                .id(sale.getId())
                .build();
    }
}
