package com.example.demo.repository.busineess;


import com.example.demo.model.business.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
    Price findById(int id);

    @Query("select p from Price p where p.dateEnd = null")
    Price getValidPrice();
}
