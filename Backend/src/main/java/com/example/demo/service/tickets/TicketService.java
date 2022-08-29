package com.example.demo.service.tickets;

import com.example.demo.dto.statistics.StatisticsPassengerResponse;
import com.example.demo.enums.TicketType;
import com.example.demo.model.tickets.Ticket;
import com.example.demo.model.users.User;
import com.example.demo.repository.tickets.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public StatisticsPassengerResponse getStatNumberOfTickets() {
        StatisticsPassengerResponse response = new StatisticsPassengerResponse();

        int monthlyTickets = 0;
        int standardTickets = 0;
        for (Ticket ticket: this.ticketRepository.findAll()) {
            if (ticket.getTicketType() == TicketType.MESECNA_KARTA){
                monthlyTickets += 1;
            }
            if (ticket.getTicketType() == TicketType.STANDARDNA_KARTA){
                standardTickets += 1;
            }
        }

        response.numberOfTickets.add(monthlyTickets);
        response.numberOfTickets.add(standardTickets);
        response.ticketTypes.add("Mesecna karta");
        response.ticketTypes.add("Standardna karta");
        return response;
    }

    public StatisticsPassengerResponse getStatNumberOfTicketsPassenger(User user) {
        StatisticsPassengerResponse response = new StatisticsPassengerResponse();

        int monthlyTickets = 0;
        int standardTickets = 0;
        for (Ticket ticket: this.ticketRepository.getByUserId(user.getId())) {
            if (ticket.getTicketType() == TicketType.MESECNA_KARTA){
                monthlyTickets += 1;
            }
            if (ticket.getTicketType() == TicketType.STANDARDNA_KARTA){
                standardTickets += 1;
            }
        }

        response.numberOfTickets.add(monthlyTickets);
        response.numberOfTickets.add(standardTickets);
        response.ticketTypes.add("Mesecna karta");
        response.ticketTypes.add("Standardna karta");
        return response;
    }

    public StatisticsPassengerResponse getStatPriceOfTickets() {
        StatisticsPassengerResponse response = new StatisticsPassengerResponse();

        int monthlyTickets = 0;
        int standardTickets = 0;
        for (Ticket ticket: this.ticketRepository.findAll()) {
            if (ticket.getTicketType() == TicketType.MESECNA_KARTA){
                monthlyTickets += ticket.getPrice();
            }
            if (ticket.getTicketType() == TicketType.STANDARDNA_KARTA){
                standardTickets += ticket.getPrice();
            }
        }

        response.numberOfTickets.add(monthlyTickets);
        response.numberOfTickets.add(standardTickets);
        response.ticketTypes.add("Mesecna karta");
        response.ticketTypes.add("Standardna karta");
        return response;
    }

    public StatisticsPassengerResponse getStatPriceOfTicketsPassenger(User user) {
        StatisticsPassengerResponse response = new StatisticsPassengerResponse();

        int monthlyTickets = 0;
        int standardTickets = 0;
        for (Ticket ticket: this.ticketRepository.getByUserId(user.getId())) {
            if (ticket.getTicketType() == TicketType.MESECNA_KARTA){
                monthlyTickets += ticket.getPrice();
            }
            if (ticket.getTicketType() == TicketType.STANDARDNA_KARTA){
                standardTickets += ticket.getPrice();
            }
        }

        response.numberOfTickets.add(monthlyTickets);
        response.numberOfTickets.add(standardTickets);
        response.ticketTypes.add("Mesecna karta");
        response.ticketTypes.add("Standardna karta");
        return response;
    }
}
