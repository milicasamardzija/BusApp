package com.example.demo.model.users.employees;

import com.example.demo.model.business.Bus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("BUSS_DRIVER")
public class BusDriver extends BussStaff {
    @OneToMany(mappedBy = "busDriver", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<Bus> buses;

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }
}
