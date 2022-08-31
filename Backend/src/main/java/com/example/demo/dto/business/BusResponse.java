package com.example.demo.dto.business;

import java.util.Date;

public class BusResponse {
    public int id;
    public String registrationNumber;
    public int garageNumber;
    public int seatNumber;
    public String manufacturer;
    public double kilometersTraveled;
    public Date endRegistrationDate;

    public BusResponse(int id, String registrationNumber, int garageNumber, int seatNumber, String manufacturer, double kilometersTraveled, Date endRegistrationDate) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.garageNumber = garageNumber;
        this.seatNumber = seatNumber;
        this.manufacturer = manufacturer;
        this.kilometersTraveled = kilometersTraveled;
        this.endRegistrationDate = endRegistrationDate;
    }
}
