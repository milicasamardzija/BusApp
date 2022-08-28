package com.example.demo.repository.tickets;

import com.example.demo.model.tickets.StandardTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardTicketRepository  extends JpaRepository<StandardTicket, Integer> {
    List<StandardTicket>  findAll();
}
