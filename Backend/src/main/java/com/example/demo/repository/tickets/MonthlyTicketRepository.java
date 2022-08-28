package com.example.demo.repository.tickets;

import com.example.demo.model.tickets.MonthlyTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyTicketRepository extends JpaRepository<MonthlyTicket, Integer> {
}
