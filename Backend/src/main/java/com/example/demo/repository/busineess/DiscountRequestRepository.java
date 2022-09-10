package com.example.demo.repository.busineess;

import com.example.demo.model.business.DiscountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRequestRepository extends JpaRepository<DiscountRequest, Integer> {
    DiscountRequest findById(int id);
}
