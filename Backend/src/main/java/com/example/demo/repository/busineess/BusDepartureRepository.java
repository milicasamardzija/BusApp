package com.example.demo.repository.busineess;

import com.example.demo.model.business.BusDeparture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusDepartureRepository extends JpaRepository<BusDeparture, Integer> {
    BusDeparture findById(int id);
}
