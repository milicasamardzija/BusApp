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

    @GetMapping(value = "/currentMonthlyTicket/{id}")
    public ResponseEntity<MonthlyTicket> currentMonthlyTicket(@PathVariable int id){
        return new ResponseEntity<>(this.monthlyTicketService.getMonthlyTicketById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/currentMonthlyTickets")
    public ResponseEntity<List<MonthlyTicket>> getMonthlyTicket(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<MonthlyTicket> ret = this.monthlyTicketService.getMonthlyTicket(user);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value="/previousTickets")
    public ResponseEntity<List<MonthlyTicket>> previousTickets(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<MonthlyTicket> ret = this.monthlyTicketService.previousTickets(user);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value="/sendTicketToMail/{id}")
    public ResponseEntity<HttpStatus> sendTicketToMail(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        this.monthlyTicketService.sendTicketToMail(id);
        return new ResponseEntity<>( HttpStatus.OK);
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

    @PutMapping(value = "/monthlyTicketReject/{id}")
    public ResponseEntity<HttpStatus> monthlyTicketReject(@PathVariable int id){
        this.monthlyTicketService.monthlyTicketReject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "checkTicket/{id}")
    public ResponseEntity<String> checkTicket (@PathVariable int id){
        return new ResponseEntity<>(this.monthlyTicketService.checkTicket(id), HttpStatus.OK);
    }
}
