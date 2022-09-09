package com.example.demo.repository.busineess;

import com.example.demo.model.business.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Discount findById(int id);
    @Query("select d from Discount d left join fetch d.passengers p where d.discountType = ?1")
    Discount getByTypeWithPassengers(String discount);
}
