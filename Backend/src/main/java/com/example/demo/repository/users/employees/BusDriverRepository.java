package com.example.demo.repository.users.employees;


import com.example.demo.model.users.employees.BusDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusDriverRepository extends JpaRepository<BusDriver, Integer> {
    BusDriver findById(int id);
    BusDriver save(BusDriver busDriver);
}
