package com.example.demo.repository.users.client;

import com.example.demo.model.users.client.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    @Query("select p from Passenger p left join fetch p.tickets where p.id = ?1")
    Passenger findByIdWithTickets(int id);
    Passenger findById(int id);
}
