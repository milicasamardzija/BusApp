package com.example.demo.controller.tickets;

import com.example.demo.dto.tickets.StandardTicketRequest;
import com.example.demo.model.tickets.StandardTicket;
import com.example.demo.model.users.User;
import com.example.demo.service.tickets.StandardTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "standardTicket")
public class StandardTicketController {

    @Autowired
    private StandardTicketService standardTicketService;

    @GetMapping
    public ResponseEntity<List<StandardTicket>> getAll(){
        return new ResponseEntity<>(this.standardTicketService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/previousTickets")
    public ResponseEntity<List<StandardTicket>> getPreviousTickets(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(this.standardTicketService.getPreviousTickets(user), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> addTicket(@RequestBody StandardTicketRequest ticket){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        this.standardTicketService.addTicket(ticket, user);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
