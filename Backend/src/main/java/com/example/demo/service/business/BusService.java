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

    public void addBus(BusRequest bus) {

    }

    public void changeBus(BusRequest bus) {

    }

    public void deleteById(int id) {

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
