package com.gabriel.store.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO;
import com.gabriel.store.models.Seller;
import com.gabriel.store.repositories.SellerRepository;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<ListSellerResponseDTO> list(final LocalDate startDate, final LocalDate endDate) {
        long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays() + 1;
        List<ListSellerResponseDTO> sellers = sellerRepository.findSellersByPeriod(
                startDate,
                endDate
        );

        if (sellers.isEmpty())
            return sellers;

        return calculateSalesDailyAverage(sellers, days);
    }

    public List<ListSellerResponseDTO> calculateSalesDailyAverage(final List<ListSellerResponseDTO> sellers, final long days) {
        return sellers.stream().map(seller -> {
            seller.setSalesDailyAverage(seller.getSalesDailyAverage() / days);
            return seller;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void updateSellerSalesAmount(final Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        seller.ifPresent(value -> value.setSalesAmount(value.getSalesAmount() + 1));
    }
}
