package com.example.demo.dto.tickets;

import com.example.demo.enums.StandardTicketType;
import com.example.demo.model.tickets.MonthlyTicket;
import com.example.demo.model.tickets.StandardTicket;

public class TicketPdfResponse {

    public String dateIssued;
    public String dateExpiration;
    public double price;
    public String cityStart;
    public String cityEnd;
    public String timeStart;
    public String dateChecked;
    public double fullPrice;
    public double discountPercentage;
    public StandardTicketType standardTicketType;

    public TicketPdfResponse(StandardTicket standardTicket) {
        if (dateChecked != null)
            dateChecked = standardTicket.getDateChecked().toString().substring(0,10);
        else
            dateChecked = null;
        dateExpiration = standardTicket.getDateExpiration().toString().substring(0,10);
        dateIssued = standardTicket.getDateIssued().toString().substring(0,10);
        price = standardTicket.getPrice();
        timeStart = standardTicket.getTimeStart();
        cityEnd = standardTicket.getCityEnd();
        cityStart = standardTicket.getCityStart();
        fullPrice = standardTicket.getFullPrice();
        discountPercentage = standardTicket.getDiscountPercentage();
        standardTicketType = standardTicket.getStandardTicketType();
    }

    public TicketPdfResponse(MonthlyTicket monthlyTicket) {
        dateExpiration = monthlyTicket.getDateExpiration().toString().substring(0,10);
        dateIssued = monthlyTicket.getDateIssued().toString().substring(0,10);
        price = monthlyTicket.getPrice();
        timeStart = monthlyTicket.getTimeStart();
        cityEnd = monthlyTicket.getCityEnd();
        cityStart = monthlyTicket.getCityStart();
        discountPercentage = monthlyTicket.getDiscountPercentage();
    }
}
