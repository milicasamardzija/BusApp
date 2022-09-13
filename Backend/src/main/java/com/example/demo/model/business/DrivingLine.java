package com.example.demo.model.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class DrivingLine {

    @Id
    @SequenceGenerator(name = "drivingLineSeqGen", sequenceName = "drivingLineSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drivingLineSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private String name;
    @Column
    private Date dateStart;
    @Column
    private Date dateEnd;
    @OneToMany(mappedBy = "drivingLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ActiveDeparture> activeDepartures;
    @OneToMany(mappedBy = "drivingLine", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<BusDeparture> busDepartures;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id")
    @JsonIgnore
    private Bus bus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<BusDeparture> getBusDepartures() {
        return busDepartures;
    }

    public void setBusDepartures(List<BusDeparture> busDepartures) {
        this.busDepartures = busDepartures;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<ActiveDeparture> getActiveDepartures() {
        return activeDepartures;
    }

    public void setActiveDepartures(List<ActiveDeparture> activeDepartures) {
        this.activeDepartures = activeDepartures;
    }
}
