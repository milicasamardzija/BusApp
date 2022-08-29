package com.example.demo.controller.tickets;

import com.example.demo.dto.statistics.StatisticsPassengerResponse;
import com.example.demo.model.users.User;
import com.example.demo.service.tickets.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "stat/getStatNumberOfTicketsPassenger")
    public ResponseEntity<StatisticsPassengerResponse> getStatNumberOfTicketsPassenger(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(this.ticketService.getStatNumberOfTicketsPassenger(user),HttpStatus.OK);
    }

    @GetMapping(value = "stat/getStatPriceOfTicketsPassenger")
    public ResponseEntity<StatisticsPassengerResponse> getStatPriceOfTicketsPassenger(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(this.ticketService.getStatPriceOfTicketsPassenger(user),HttpStatus.OK);
    }

    @GetMapping(value = "stat/getStatNumberOfTickets")
    public ResponseEntity<StatisticsPassengerResponse> getStatNumberOfTickets(){
        return new ResponseEntity<>(this.ticketService.getStatNumberOfTickets(),HttpStatus.OK);
    }

    @GetMapping(value = "stat/getStatPriceOfTickets")
    public ResponseEntity<StatisticsPassengerResponse> getStatPriceOfTickets(){
        return new ResponseEntity<>(this.ticketService.getStatPriceOfTickets(),HttpStatus.OK);
    }
}
