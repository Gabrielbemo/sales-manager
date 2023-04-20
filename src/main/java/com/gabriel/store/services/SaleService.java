package com.gabriel.store.services;

import com.gabriel.store.models.Sale;
import com.gabriel.store.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    public final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }

    @Transactional
    public Sale create(Sale sale){
        return saleRepository.save(sale);
    }
}
