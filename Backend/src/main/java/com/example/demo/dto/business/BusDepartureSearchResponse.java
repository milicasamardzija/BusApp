package com.example.demo.dto.business;

import java.util.Date;

public class BusDepartureSearchResponse {
    public int drivingLineId;
    public int activeDepartureId;
    public String cityStart;
    public String cityEnd;
    public Date timeStart;
    public Date timeEnd;
    public double price;
    public double priceMonthly;
    public int seats;

    public BusDepartureSearchResponse(int drivingLineId, int activeDepartureId, String cityStart, String cityEnd, Date timeStart, Date timeEnd, double price, double priceMonthly, int seats) {
        this.drivingLineId = drivingLineId;
        this.activeDepartureId = activeDepartureId;
        this.cityStart = cityStart;
        this.cityEnd = cityEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.price = price;
        this.priceMonthly = priceMonthly;
        this.seats = seats;
    }
}
