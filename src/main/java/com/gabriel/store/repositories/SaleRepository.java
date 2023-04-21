package com.gabriel.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.store.models.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
