package com.example.demo.controller.business;

import com.example.demo.dto.business.BusRequest;
import com.example.demo.dto.business.DrivingLineResponse;
import com.example.demo.model.business.DrivingLine;
import com.example.demo.service.business.DrivingLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "drivingLine")
public class DrivingLineController {

    @Autowired
    private DrivingLineService drivingLineService;

    @GetMapping
    public ResponseEntity<List<DrivingLineResponse>> getAll(){
        List<DrivingLineResponse> ret = new ArrayList<>();
        for (DrivingLine line : this.drivingLineService.getAll()
        ) {
            ret.add(new DrivingLineResponse());
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addBus(@RequestBody BusRequest bus){

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> changeBus(@RequestBody BusRequest bus){

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){

        return new ResponseEntity<>( HttpStatus.OK);
    }

}
