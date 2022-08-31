package com.example.demo.model.business;

import javax.persistence.*;

@Entity
public class Price {

    @Id
    @SequenceGenerator(name = "priceSeqGen", sequenceName = "priceSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priceSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private double pricePerKilometer;
    @Column
    private double pricePerKilometerMonthlyTicket;

    public Price() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    public void setPricePerKilometer(double pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }

    public double getPricePerKilometerMonthlyTicket() {
        return pricePerKilometerMonthlyTicket;
    }

    public void setPricePerKilometerMonthlyTicket(double pricePerKilometerMonthlyTicket) {
        this.pricePerKilometerMonthlyTicket = pricePerKilometerMonthlyTicket;
    }
}
