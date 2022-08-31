package com.example.demo.repository.busineess;

import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.ActiveDeparture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveDepartureRepository extends JpaRepository<ActiveDeparture, Integer> {
    @Query("select a from ActiveDeparture a left join fetch a.drivingLine line where line.id = ?1 and a.dayOfWeek = ?2")
    ActiveDeparture findByDrivingLine(int id, DaysOfWeek day);
    ActiveDeparture findById(int id);
}
