package com.example.demo.repository.tickets;

import com.example.demo.model.tickets.MonthlyTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MonthlyTicketRepository extends JpaRepository<MonthlyTicket, Integer> {

    MonthlyTicket findById(int id);
    @Query("select mt from MonthlyTicket mt where mt.passenger.id = ?1 and mt.approved = true and mt.dateExpiration > ?2")
    List<MonthlyTicket> getMonthlyTicketByUser(int id, Date date);
    @Query("select mt from MonthlyTicket mt where mt.approved = false")
    List<MonthlyTicket> getRequests();
    @Query("select mt from MonthlyTicket mt where mt.passenger.id = ?1 and mt.approved = true and mt.dateExpiration < ?2")
    List<MonthlyTicket> previousTickets(int id, Date date);
}
