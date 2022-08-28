package com.example.demo.service.tickets;

import com.example.demo.repository.tickets.MonthlyTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlyTicketService {

    @Autowired
    private MonthlyTicketRepository monthlyTicketRepository;
}
