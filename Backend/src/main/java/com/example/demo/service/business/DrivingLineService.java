package com.example.demo.service.business;

import com.example.demo.model.business.DrivingLine;
import com.example.demo.repository.busineess.DrivingLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrivingLineService {

    @Autowired
    private DrivingLineRepository drivingLineRepository;

    public List<DrivingLine> getAll() {
        return this.drivingLineRepository.findAll();
    }
}
