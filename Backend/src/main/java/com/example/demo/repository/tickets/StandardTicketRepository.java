package com.example.demo.repository.tickets;

import com.example.demo.model.tickets.StandardTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StandardTicketRepository  extends JpaRepository<StandardTicket, Integer> {

    List<StandardTicket>  findAll();
    @Query("select t from StandardTicket t where t.passenger.id = ?1 and t.dateChecked < ?2")
    List<StandardTicket> getPreviousTickets(int id, Date date);
}
