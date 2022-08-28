package com.example.demo.service.business;

import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.ActiveDeparture;
import com.example.demo.repository.busineess.ActiveDepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveDepartureService {

    @Autowired
    private ActiveDepartureRepository activeDepartureRepository;

    public void update(ActiveDeparture activeDeparture) {
        this.activeDepartureRepository.save(activeDeparture);
    }

    public int findByDrivingLine(int id, DaysOfWeek day) {
        return this.activeDepartureRepository.findByDrivingLine(id, day).getId();
    }
}
