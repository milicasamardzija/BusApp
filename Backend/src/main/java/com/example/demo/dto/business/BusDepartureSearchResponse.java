package com.example.demo.dto.business;


public class BusDepartureSearchResponse {
    public int drivingLineId;
    public int activeDepartureId;
    public String cityStart;
    public String cityEnd;
    public String timeStart;
    public String timeEnd;
    public double price;
    public double priceMonthly;
    public double fullPrice;
    public double discountPercentage;
    public int seats;

    public BusDepartureSearchResponse(int drivingLineId, int activeDepartureId, String cityStart, String cityEnd, String timeStart, String timeEnd, double price, double priceMonthly,int seats,  double fullPrice, double discountPercentage) {
        this.drivingLineId = drivingLineId;
        this.activeDepartureId = activeDepartureId;
        this.cityStart = cityStart;
        this.cityEnd = cityEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.price = price;
        this.priceMonthly = priceMonthly;
        this.fullPrice = fullPrice;
        this.discountPercentage = discountPercentage;
        this.seats = seats;
    }
}
