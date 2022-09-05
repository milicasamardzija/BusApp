package com.example.demo.repository.busineess;

import com.example.demo.model.business.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    Bus findById(int id);

    @Query("select b from Bus b left join fetch b.drivingLines lines where b.id = ?1")
    Bus findByIdWithDrivingLines(int busId);
    @Query("select b from Bus b left join fetch b.drivingLines lines where b.id = ?1")
    List<Bus> getAvailableBuses();

}
