package com.gabriel.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO;
import com.gabriel.store.models.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = """
             SELECT new com.gabriel.store.controllers.dtos.out.ListSellerResponseDTO(
                se.name as name,  se.salesAmount as salesAmount, count(sa) as salesDailyAverage)
                FROM Seller se
                LEFT JOIN Sale sa ON se.id = sa.seller.id and sa.saleDate BETWEEN :startDate AND :endDate
                GROUP BY se.id, se.name
            """)
    List<ListSellerResponseDTO> findSellersByPeriod(
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );
}
