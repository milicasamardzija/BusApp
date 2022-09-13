package com.example.demo.controller.tickets;

import com.example.demo.dto.statistics.StatisticRequest;
import com.example.demo.dto.statistics.StatisticsPassengerResponse;
import com.example.demo.model.users.User;
import com.example.demo.service.tickets.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "stat/getStatPriceOfTicketsPassenger")
    public ResponseEntity<StatisticsPassengerResponse> getStatPriceOfTicketsPassenger(@RequestBody StatisticRequest statisticRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(this.ticketService.getStatPriceOfTicketsPassenger(user, statisticRequest),HttpStatus.OK);
    }

    @PostMapping(value = "stat/getStatNumberOfTickets")
    public ResponseEntity<StatisticsPassengerResponse> getStatNumberOfTickets(@RequestBody StatisticRequest statisticRequest){
        return new ResponseEntity<>(this.ticketService.getStatNumberOfTickets(statisticRequest),HttpStatus.OK);
    }

    @PostMapping(value = "stat/getStatPriceOfTickets")
    public ResponseEntity<StatisticsPassengerResponse> getStatPriceOfTickets(@RequestBody StatisticRequest statisticRequest){
        return new ResponseEntity<>(this.ticketService.getStatPriceOfTickets(statisticRequest),HttpStatus.OK);
    }
}
