package com.example.demo.repository.tickets;

import com.example.demo.model.tickets.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>  {
    @Query("select t from Ticket t where t.passenger.id = ?1")
    List<Ticket> getByUserId(int id);
}
