package com.example.demo.controller.tickets;

import com.example.demo.dto.tickets.MonthlyTicketRequest;
import com.example.demo.model.tickets.MonthlyTicket;
import com.example.demo.model.users.User;
import com.example.demo.service.tickets.MonthlyTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "monthlyTicket")
public class MonthlyTicketController {

    @Autowired
    private MonthlyTicketService monthlyTicketService;

    @GetMapping(value = "/requests")
    public ResponseEntity<List<MonthlyTicket>> getRequests(){
        return new ResponseEntity<>(this.monthlyTicketService.getRequests(), HttpStatus.OK);
    }


    @GetMapping(value = "/currentMonthlyTicket")
    public ResponseEntity<MonthlyTicket> getMonthlyTicket(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        MonthlyTicket ret = this.monthlyTicketService.getMonthlyTicket(user);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addTicket(@RequestBody MonthlyTicketRequest ticket){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        this.monthlyTicketService.addTicket(ticket, user);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping(value = "/monthlyTicketApprove/{id}")
    public ResponseEntity<MonthlyTicket> monthlyTicketApprove(@PathVariable int id){
        return new ResponseEntity<>(this.monthlyTicketService.monthlyTicketApprove(id), HttpStatus.OK);
    }
}
