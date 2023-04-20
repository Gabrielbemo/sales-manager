package com.gabriel.store.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The sale date cannot be null")
    private LocalDate saleDate;

    @NotNull(message = "The sale value cannot be null")
    private Long saleValue;

    @ManyToOne()
    @JoinColumn(name = "fkSeller")
    @NotNull(message = "The seller cannot be null")
    private Seller seller;
}
