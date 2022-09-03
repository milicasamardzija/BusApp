package com.example.demo.service.business;

import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.ActiveDeparture;
import com.example.demo.repository.busineess.ActiveDepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActiveDepartureService {

    @Autowired
    private ActiveDepartureRepository activeDepartureRepository;

    public void update(ActiveDeparture activeDeparture) {
        this.activeDepartureRepository.save(activeDeparture);
    }

    public ActiveDeparture findByDrivingLine(int id, DaysOfWeek day) {
        return this.activeDepartureRepository.findByDrivingLine(id, day);
    }

    public ActiveDeparture getById(int activeDepartureId) {
        return this.activeDepartureRepository.findById(activeDepartureId);
    }

    public void save(ActiveDeparture activeDeparture) {
        this.activeDepartureRepository.save(activeDeparture);
    }

    @Scheduled(cron = "${greeting.cron}")
    private void removeSeats(){
        Date date = new Date();
        int yesterday = date.getDay() - 1;
        if (yesterday == -1){
            yesterday = 6;
        }

        for (ActiveDeparture activeDeparture: this.getDepartures()) {
            if (activeDeparture.getDayOfWeek().ordinal() == yesterday){
                activeDeparture.setSeats(activeDeparture.getDrivingLine().getBus().getSeatNumber());
                this.activeDepartureRepository.save(activeDeparture);
            }
        }
    }

    private List<ActiveDeparture> getDepartures() {
        return this.activeDepartureRepository.getDepartures();
    }

    private List<ActiveDeparture> getAll() {
        return this.activeDepartureRepository.findAll();
    }
}
