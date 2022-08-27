package com.example.demo.model.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Bus {

    @Id
    @SequenceGenerator(name = "busSeqGen", sequenceName = "busSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "busSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private String registrationNumber;
    @Column
    private int garageNumber;
    @Column
    private int seatNumber;
    @Column
    private String manufacturer;
    @Column
    private double kilometersTraveled;
    @Column
    private Date endRegistrationDate;
    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DrivingLine> drivingLines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getGarageNumber() {
        return garageNumber;
    }

    public void setGarageNumber(int garageNumber) {
        this.garageNumber = garageNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getKilometersTraveled() {
        return kilometersTraveled;
    }

    public void setKilometersTraveled(double kilometersTraveled) {
        this.kilometersTraveled = kilometersTraveled;
    }

    public Date getEndRegistrationDate() {
        return endRegistrationDate;
    }

    public void setEndRegistrationDate(Date endRegistrationDate) {
        this.endRegistrationDate = endRegistrationDate;
    }

    public List<DrivingLine> getDrivingLines() {
        return drivingLines;
    }

    public void setDrivingLines(List<DrivingLine> drivingLines) {
        this.drivingLines = drivingLines;
    }
}
