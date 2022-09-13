package com.example.demo.service.users.employees;

import com.example.demo.model.users.employees.BusController;
import com.example.demo.repository.users.employees.BusControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusControllerService {

    @Autowired
    private BusControllerRepository busControllerRepository;

    public BusController getById(int id){
        return this.busControllerRepository.findById(id);
    }

    public void save(BusController busController) {
        this.busControllerRepository.save(busController);
    }

    public List<BusController> findAll() {
        return this.busControllerRepository.findAll();
    }
}
