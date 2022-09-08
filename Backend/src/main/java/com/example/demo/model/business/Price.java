package com.example.demo.model.business;

import javax.persistence.*;
import java.util.Date;

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
    @Column
    private Date dateStart;
    @Column
    private Date dateEnd;

    public Price() {}

    public Price(double pricePerKilometer, double pricePerKilometerMonthlyTicket, Date dateStart, Date dateEnd) {
        this.pricePerKilometer = pricePerKilometer;
        this.pricePerKilometerMonthlyTicket = pricePerKilometerMonthlyTicket;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
