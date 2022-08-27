package com.example.demo.repository.busineess;

import com.example.demo.model.business.DrivingLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingLineRepository extends JpaRepository<DrivingLine, Integer> {
}
