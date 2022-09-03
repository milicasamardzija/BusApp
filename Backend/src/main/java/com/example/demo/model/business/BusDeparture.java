package com.example.demo.model.business;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BusDeparture {

    @Id
    @SequenceGenerator(name = "busDepartureSeqGen", sequenceName = "busDepartureSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "busDepartureSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private String city;
    @Column
    private double km;
    @Column
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_line_id")
    private DrivingLine drivingLine;

    public BusDeparture(){}

    public BusDeparture(String city, double km, String time) {
        this.city = city;
        this.km = km;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DrivingLine getDrivingLine() {
        return drivingLine;
    }

    public void setDrivingLine(DrivingLine drivingLine) {
        this.drivingLine = drivingLine;
    }
}
