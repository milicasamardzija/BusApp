package com.example.demo.repository.busineess;

import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.ActiveDeparture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActiveDepartureRepository extends JpaRepository<ActiveDeparture, Integer> {
    @Query("select a from ActiveDeparture a left join fetch a.drivingLine line where line.id = ?1 and a.dayOfWeek = ?2 and a.date = ?3")
    ActiveDeparture findByDrivingLine(int id, DaysOfWeek day, Date date);
    ActiveDeparture findById(int id);
    @Query("select distinct a  from ActiveDeparture a left join fetch a.drivingLine line left join fetch line.bus b where a.drivingLine.id = line.id and line.bus.id = b.id")
    List<ActiveDeparture> getDepartures();
}
