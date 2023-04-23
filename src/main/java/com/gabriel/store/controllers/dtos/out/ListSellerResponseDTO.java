package com.gabriel.store.controllers.dtos.out;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListSellerResponseDTO {

    @NotEmpty(message = "The seller name cannot be empty")
    private String name;

    @NotNull(message = "The seller sales amount cannot be null")
    private Long salesAmount;

    @NotNull(message = "The seller sales daily average cannot be null")
    private Double salesDailyAverage;
}
