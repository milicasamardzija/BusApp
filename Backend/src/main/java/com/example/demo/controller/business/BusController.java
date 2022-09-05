package com.example.demo.controller.business;

import com.example.demo.dto.business.BusRequest;
import com.example.demo.dto.business.BusResponse;
import com.example.demo.model.business.Bus;
import com.example.demo.service.business.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<List<BusResponse>> getAll(){
        List<BusResponse> ret = new ArrayList<>();

        for (Bus bus: this.busService.getAll() ) {
            ret.add(new BusResponse(bus.getId(), bus.getRegistrationNumber(), bus.getGarageNumber(), bus.getSeatNumber(), bus.getManufacturer(), bus.getKilometersTraveled(), bus.getEndRegistrationDate()));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BusResponse> get(@PathVariable int id){
        Bus bus = this.busService.getById(id);
        return new ResponseEntity<>(new BusResponse(bus.getId(), bus.getRegistrationNumber(), bus.getGarageNumber(), bus.getSeatNumber(), bus.getManufacturer(), bus.getKilometersTraveled(), bus.getEndRegistrationDate()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addBus(@RequestBody BusRequest bus){
        this.busService.addBus(bus);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> changeBus(@RequestBody BusRequest bus){
        this.busService.changeBus(bus);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        this.busService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
