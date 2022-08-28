package com.example.demo.controller.business;

import com.example.demo.dto.business.BusRequest;
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
    public ResponseEntity<List<Bus>> getAll(){
        return new ResponseEntity<>(this.busService.getAll(), HttpStatus.OK);
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
