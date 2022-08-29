package com.example.demo.repository.busineess;

import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.DrivingLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrivingLineRepository extends JpaRepository<DrivingLine, Integer> {
    @Query("select d from DrivingLine d left join fetch d.activeDepartures active where d.id = active.drivingLine.id and active.dayOfWeek = ?1")
    List<DrivingLine> getAll(DaysOfWeek day);
    @Query("select distinct d from DrivingLine d left join fetch d.activeDepartures active left join fetch d.bus b where d.id = active.drivingLine.id")
    List<DrivingLine> getAllWithWeeks();
}
