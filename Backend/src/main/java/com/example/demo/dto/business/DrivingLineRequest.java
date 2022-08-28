package com.example.demo.dto.business;

import com.example.demo.enums.DaysOfWeek;

import java.util.Date;
import java.util.List;

public class DrivingLineRequest {

    public String name;
    public Date dateStart;
    public Date dateEnd;
    public List<DaysOfWeek> daysOfWeek;
    public int busId;
    public List<BusDepartureRequest> busDepartures;

}
