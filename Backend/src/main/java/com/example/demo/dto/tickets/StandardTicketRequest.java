package com.example.demo.dto.tickets;


import com.example.demo.enums.StandardTicketType;

import java.util.Date;

public class StandardTicketRequest {
    public String cityStart;
    public String cityEnd;
    public String timeStart;
    public double price;
    public int activeDepartureId;
    public double discountPercentage;
    public double fullPrice;
    public StandardTicketType standardTicketType;
    public Date date;
}
