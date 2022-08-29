package com.example.demo.dto.business;

import com.example.demo.model.business.ActiveDeparture;
import com.example.demo.model.business.BusDeparture;

import java.util.List;

public class DrivingLineResponse {
    public int id;
    public String name;
    public List<ActiveDeparture> daysOfWeek;
    public List<BusDeparture> busDepartures;

    public DrivingLineResponse(int id, String name, List<ActiveDeparture> daysOfWeek, List<BusDeparture> busDepartures) {
        this.id = id;
        this.name = name;
        this.daysOfWeek = daysOfWeek;
        this.busDepartures = busDepartures;
    }
}
