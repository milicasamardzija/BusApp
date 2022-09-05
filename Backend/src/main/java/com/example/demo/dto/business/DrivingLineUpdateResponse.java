package com.example.demo.dto.business;

import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.ActiveDeparture;

import java.util.Date;
import java.util.List;

public class DrivingLineUpdateResponse {

    public int id;
    public String name;
    public Date dateStart;
    public Date dateEnd;
    public List<DaysOfWeek> daysOfWeek;
    public int busId;
    public List<BusDepartureResponse> busDepartures;

    public DrivingLineUpdateResponse(int id, String name, Date dateStart, Date dateEnd, List<DaysOfWeek> daysOfWeek, int busId, List<BusDepartureResponse> busDepartures) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.daysOfWeek = daysOfWeek;
        this.busId = busId;
        this.busDepartures = busDepartures;
    }
}
