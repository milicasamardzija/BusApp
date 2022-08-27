package com.example.demo.controller.business;

import com.example.demo.dto.business.BusDepartureRequest;
import com.example.demo.dto.business.BusDepartureResponse;
import com.example.demo.model.business.BusDeparture;
import com.example.demo.service.business.BusDepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "busDeparture")
public class BusDepartureController {

    @Autowired
    private BusDepartureService busDepartureService;

    @GetMapping
    public ResponseEntity<List<BusDepartureResponse>> getAll(){
        List<BusDepartureResponse> ret = new ArrayList<>();
        for (BusDeparture departure : this.busDepartureService.getAll()
        ) {
            ret.add(new BusDepartureResponse());
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addBus(@RequestBody BusDepartureRequest departure){

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> changeBus(@RequestBody BusDepartureRequest departure){

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){

        return new ResponseEntity<>( HttpStatus.OK);
    }
}

