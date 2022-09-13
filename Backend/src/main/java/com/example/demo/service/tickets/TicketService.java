package com.example.demo.service.tickets;

import com.example.demo.dto.statistics.StatisticRequest;
import com.example.demo.dto.statistics.StatisticsPassengerResponse;
import com.example.demo.enums.TicketType;
import com.example.demo.model.tickets.Ticket;
import com.example.demo.model.users.User;
import com.example.demo.repository.tickets.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public StatisticsPassengerResponse getStatNumberOfTickets(StatisticRequest statisticRequest) {
        int year = Integer.parseInt(statisticRequest.year.split("-")[0]) - 1900;
        StatisticsPassengerResponse response = new StatisticsPassengerResponse();
        List<Integer> monthlyTickets = new ArrayList<Integer>(Arrays.asList(
                0,0,0,0,0,0,0,0,0,0,0,0
        ));
        List<Integer> standardTickets = new ArrayList<Integer>(Arrays.asList(
                0,0,0,0,0,0,0,0,0,0,0,0
        ));
        List<String> mounths = new ArrayList<String>(Arrays.asList(
                "Januar","Februar","Mart","April","Maj","Jun","Jul","Avgust","Septembar","Oktobar","Novembar","Decembar"
        ));
        for (Ticket ticket: this.ticketRepository.findAll()) {
            if (ticket.getDateIssued().getYear() == year){
                if (ticket.getDateIssued().getMonth() == Calendar.JANUARY){
                    calculateTickets(ticket,0,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.FEBRUARY){
                    calculateTickets(ticket,1,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.MARCH){
                    calculateTickets(ticket,2,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.APRIL){
                    calculateTickets(ticket,3,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.MAY){
                    calculateTickets(ticket,1,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.JUNE){
                    calculateTickets(ticket,5,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.JULY){
                    calculateTickets(ticket,6,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.AUGUST){
                    calculateTickets(ticket,7,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.SEPTEMBER){
                    calculateTickets(ticket,8,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.OCTOBER){
                    calculateTickets(ticket,9,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.NOVEMBER){
                    calculateTickets(ticket,10,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.DECEMBER){
                    calculateTickets(ticket,11,monthlyTickets,standardTickets);
                }
            }
        }

        response.y3 = monthlyTickets;
        response.y4 = standardTickets;
        response.x = mounths;
        return response;
    }

    private void calculateTickets(Ticket ticket, int id, List<Integer> monthlyTickets, List<Integer> standardTickets) {
        if (ticket.getTicketType() == TicketType.MESECNA_KARTA){
            monthlyTickets.set(id, monthlyTickets.get(id) + 1);
        }
        if (ticket.getTicketType() == TicketType.STANDARDNA_KARTA){
            standardTickets.set(id,standardTickets.get(id) + 1);
        }
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

        response.y3.add(monthlyTickets);
        response.y3.add(standardTickets);
        response.x.add("Mesecna karta");
        response.x.add("Standardna karta");
        return response;
    }

    public StatisticsPassengerResponse getStatPriceOfTickets(StatisticRequest statisticRequest) {
        int year = Integer.parseInt(statisticRequest.year.split("-")[0]) - 1900;
        StatisticsPassengerResponse response = new StatisticsPassengerResponse();
        List<Double> monthlyTickets = new ArrayList<Double>(Arrays.asList(
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0
        ));
        List<Double> standardTickets = new ArrayList<Double>(Arrays.asList(
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0
        ));
        List<String> mounths = new ArrayList<String>(Arrays.asList(
                "Januar","Februar","Mart","April","Maj","Jun","Jul","Avgust","Septembar","Oktobar","Novembar","Decembar"
        ));
        for (Ticket ticket: this.ticketRepository.findAll()) {
            if (ticket.getDateIssued().getYear() == year){
                if (ticket.getDateIssued().getMonth() == Calendar.JANUARY){
                    calculateMoney(ticket,0,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.FEBRUARY){
                    calculateMoney(ticket,1,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.MARCH){
                    calculateMoney(ticket,2,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.APRIL){
                    calculateMoney(ticket,3,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.MAY){
                    calculateMoney(ticket,1,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.JUNE){
                    calculateMoney(ticket,5,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.JULY){
                    calculateMoney(ticket,6,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.AUGUST){
                    calculateMoney(ticket,7,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.SEPTEMBER){
                    calculateMoney(ticket,8,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.OCTOBER){
                    calculateMoney(ticket,9,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.NOVEMBER){
                    calculateMoney(ticket,10,monthlyTickets,standardTickets);
                }
                else if (ticket.getDateIssued().getMonth() == Calendar.DECEMBER){
                    calculateMoney(ticket,11,monthlyTickets,standardTickets);
                }
            }
        }

        response.y1 = monthlyTickets;
        response.y2 = standardTickets;
        response.x = mounths;
        return response;
    }

    void calculateMoney(Ticket ticket, int id, List<Double> monthlyTickets, List<Double> standardTickets){
        if (ticket.getTicketType() == TicketType.MESECNA_KARTA){
            monthlyTickets.set(id, monthlyTickets.get(id) + ticket.getPrice());
        }
        if (ticket.getTicketType() == TicketType.STANDARDNA_KARTA){
            standardTickets.set(id,standardTickets.get(id) + ticket.getPrice());
        }
    }

    public StatisticsPassengerResponse getStatPriceOfTicketsPassenger(User user, StatisticRequest statisticRequest) {
        int year = Integer.parseInt(statisticRequest.year.split("-")[0]) - 1900;
        int month = Integer.parseInt(statisticRequest.year.split("-")[1]) - 1;
        StatisticsPassengerResponse response = new StatisticsPassengerResponse();
        List<Double> monthlyTickets = new ArrayList<Double>(Arrays.asList(
                0.0,0.0
        ));
        List<Double> standardTickets = new ArrayList<Double>(Arrays.asList(
                0.0,0.0
        ));
        List<Double> usteda = new ArrayList<Double>(Arrays.asList(
                0.0,0.0
        ));
        List<String> mounths = new ArrayList<String>(Arrays.asList(
                "Standardne karte","Mesecne karte"
        ));
        for (Ticket ticket: this.ticketRepository.getByUserId(user.getId())) {
            if (ticket.getDateIssued().getMonth() == month && ticket.getDateIssued().getYear() == year){
                calculateMoneyPassenger(ticket,monthlyTickets,standardTickets, usteda);
            }
        }

        response.y1 = monthlyTickets;
        response.y2 = standardTickets;
        response.y5 = usteda;
        response.x = mounths;
        return response;
    }

    private void calculateMoneyPassenger(Ticket ticket,List<Double> fullPrice, List<Double> discountPrice, List<Double> usteda) {
        if (ticket.getTicketType() == TicketType.STANDARDNA_KARTA){
            fullPrice.set(0, fullPrice.get(0) + ticket.getFullPrice());
            discountPrice.set(0, discountPrice.get(0) + ticket.getPrice());
            usteda.set(0,usteda.get(0) + (ticket.getFullPrice() - ticket.getPrice()));
        }
        if (ticket.getTicketType() == TicketType.MESECNA_KARTA){
            fullPrice.set(1, fullPrice.get(1) + ticket.getFullPrice());
            discountPrice.set(1, discountPrice.get(1) + ticket.getPrice());
            usteda.set(1,usteda.get(1) + (ticket.getFullPrice() - ticket.getPrice()));
        }
    }

}
