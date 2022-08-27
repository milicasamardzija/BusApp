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
    private String Name;
    @Column
    private Date dateEnd;
    @Column
    private String activeOn;
    @OneToMany(mappedBy = "drivingLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BusDeparture> busDepartures;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getActiveOn() {
        return activeOn;
    }

    public void setActiveOn(String activeOn) {
        this.activeOn = activeOn;
    }

    public List<BusDeparture> getBusDepartures() {
        return busDepartures;
    }

    public void setBusDepartures(List<BusDeparture> busDepartures) {
        this.busDepartures = busDepartures;
    }
}
