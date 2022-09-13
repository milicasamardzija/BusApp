package com.example.demo.service.business;

import com.example.demo.dto.business.BusRequest;
import com.example.demo.model.business.Bus;
import com.example.demo.model.business.DrivingLine;
import com.example.demo.model.users.employees.BusDriver;
import com.example.demo.repository.busineess.BusRepository;
import com.example.demo.service.users.employees.BusDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private BusDriverService busDriverService;

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

        BusDriver busDriver = this.busDriverService.getByIdWithBuses(busRequest.idBusDriver);
        bus.setBusDriver(busDriver);

        busDriver.getBuses().add(bus);

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

        BusDriver busDriver = this.busDriverService.getByIdWithBuses(busRequest.idBusDriver);
        bus.setBusDriver(busDriver);

        List<Bus> buses = busDriver.getBuses();
        buses.add(bus);
        busDriver.setBuses(buses);
        //this.busDriverService.save(busDriver);

        this.busRepository.save(bus);
    }

    public void deleteById(int id) {
        Bus bus = this.busRepository.findById(id);
        BusDriver busDriver = bus.getBusDriver();

        List<Bus> buses = busDriver.getBuses();
        buses.remove(bus);
        busDriver.setBuses(buses);
        this.busDriverService.save(busDriver);
        this.busRepository.delete(bus);
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

    public Bus getById(int id) {
        return this.busRepository.findById(id);
    }

    public void save(Bus bus) { this.busRepository.save(bus);
    }
}
