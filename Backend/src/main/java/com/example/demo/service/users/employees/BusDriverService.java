package com.example.demo.service.users.employees;

import com.example.demo.model.users.employees.BusDriver;
import com.example.demo.repository.users.employees.BusDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusDriverService {

    @Autowired
    private BusDriverRepository busDriverRepository;

    public BusDriver getById(int id){
        return this.busDriverRepository.findById(id);
    }

    public void save(BusDriver busDriver) {
        this.busDriverRepository.save(busDriver);
    }

    public BusDriver getByIdWithBuses(int idBusDriver) {
        return this.busDriverRepository.findById(idBusDriver);
    }

    public List<BusDriver> findAll() {
        return this.busDriverRepository.findAll();
    }
}
