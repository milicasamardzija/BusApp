package com.example.demo.service.business;

import com.example.demo.model.business.BusDeparture;
import com.example.demo.repository.busineess.BusDepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusDepartureService {

    @Autowired
    private BusDepartureRepository busDepartureRepository;

    public List<BusDeparture> getAll() {
        return this.busDepartureRepository.findAll();
    }
}
