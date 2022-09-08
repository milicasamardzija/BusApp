package com.example.demo.model.business;

import com.example.demo.enums.DaysOfWeek;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ActiveDeparture {

    @Id
    @SequenceGenerator(name = "activeSeqGen", sequenceName = "activeSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activeSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private DaysOfWeek dayOfWeek;
    @Column
    private int seats;
    @Column
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_line_id")
    private DrivingLine drivingLine;

    public ActiveDeparture(){}

    public ActiveDeparture(Date date,DaysOfWeek dayOfWeek, int seats) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DaysOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DaysOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public DrivingLine getDrivingLine() {
        return drivingLine;
    }

    public void setDrivingLine(DrivingLine drivingLine) {
        this.drivingLine = drivingLine;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
