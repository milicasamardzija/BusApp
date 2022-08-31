package com.example.demo.service.business;

import com.example.demo.dto.business.BusRequest;
import com.example.demo.model.business.Bus;
import com.example.demo.repository.busineess.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> getAll() {
        return this.busRepository.findAll();
    }

    public void addBus(BusRequest busRequest) {
        Bus bus = new Bus();
        bus.setManufacturer(busRequest.manufacturer);
        bus.setGarageNumber(busRequest.garageNumber);
        bus.setKilometersTraveled(busRequest.kilometersTraveled);
        bus.setSeatNumber(busRequest.seatNumber);
        bus.setDrivingLines(null);
        bus.setEndRegistrationDate(busRequest.endRegistrationDate);
        bus.setRegistrationNumber(busRequest.registrationNumber);
        this.busRepository.save(bus);
    }

    public void changeBus(BusRequest busRequest) {
        Bus bus = this.busRepository.findById(busRequest.id);
        bus.setManufacturer(busRequest.manufacturer);
        bus.setGarageNumber(busRequest.garageNumber);
        bus.setKilometersTraveled(busRequest.kilometersTraveled);
        bus.setSeatNumber(busRequest.seatNumber);
        bus.setEndRegistrationDate(busRequest.endRegistrationDate);
        bus.setRegistrationNumber(busRequest.registrationNumber);
        this.busRepository.save(bus);
    }

    public void deleteById(int id) {
        this.busRepository.deleteById(id);
    }

    public Bus findById(int busId) {
        return this.busRepository.findById(busId);
    }

    public Bus findByIdWithDrivingLines(int busId) {
        return this.busRepository.findByIdWithDrivingLines(busId);
    }

    public void update(Bus bus) {
        this.busRepository.save(bus);
    }
}
