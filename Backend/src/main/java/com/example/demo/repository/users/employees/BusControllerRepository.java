package com.example.demo.repository.users.employees;

import com.example.demo.model.users.employees.BusController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusControllerRepository extends JpaRepository<BusController, Integer> {
    BusController findById(int id);
}
