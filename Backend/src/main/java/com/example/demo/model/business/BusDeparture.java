package com.example.demo.model.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private String cityStart;
    @Column
    private String cityEnd;
    @Column
    private double price;
    @Column
    private double km;
    @Column
    private Date startTime;
    @Column
    private Date endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_line_id")
    private DrivingLine drivingLine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityStart() {
        return cityStart;
    }

    public void setCityStart(String cityStart) {
        this.cityStart = cityStart;
    }

    public String getCityEnd() {
        return cityEnd;
    }

    public void setCityEnd(String cityEnd) {
        this.cityEnd = cityEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public DrivingLine getDrivingLine() {
        return drivingLine;
    }

    public void setDrivingLine(DrivingLine drivingLine) {
        this.drivingLine = drivingLine;
    }
}
