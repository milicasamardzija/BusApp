package com.example.demo.dto.business;


public class BusDepartureResponse {

    public int id;
    public String city;
    public double km;
    public String time;

    public BusDepartureResponse(int id, String city, double km, String time) {
        this.id = id;
        this.city = city;
        this.km = km;
        this.time = time;
    }
}
