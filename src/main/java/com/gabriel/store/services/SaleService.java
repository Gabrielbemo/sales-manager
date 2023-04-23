package com.gabriel.store.services;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.gabriel.store.models.Sale;
import com.gabriel.store.repositories.SaleRepository;

@Service
public class SaleService {

    public final SaleRepository saleRepository;

    public final SellerService sellerService;

    public SaleService(SaleRepository saleRepository, SellerService sellerService) {
        this.saleRepository = saleRepository;
        this.sellerService = sellerService;
    }

    @Transactional
    public Sale create(Sale sale) {
        sellerService.updateSellerSalesAmount(sale.getSeller().getId());
        return saleRepository.save(sale);
    }
}
