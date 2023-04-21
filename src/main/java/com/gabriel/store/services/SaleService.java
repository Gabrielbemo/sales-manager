package com.gabriel.store.services;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.gabriel.store.models.Sale;
import com.gabriel.store.repositories.SaleRepository;

@Service
public class SaleService {

    public final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional
    public Sale create(Sale sale) {
        return saleRepository.save(sale);
    }
}
