package com.example.demo.controller.users.client;

import com.example.demo.dto.users.client.PassengerResponse;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.service.users.client.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<PassengerResponse>> getAll(){
        List<PassengerResponse> ret = new ArrayList<>();
        for (Passenger passenger : this.passengerService.getAll()
             ) {
            ret.add(new PassengerResponse(passenger.getName(), passenger.getSurname(), passenger.getTelephone(), passenger.getEmail()));
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }
}
