package com.example.demo.controller.business;

import com.example.demo.dto.business.PriceRequest;
import com.example.demo.model.business.Price;
import com.example.demo.service.business.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> getAll(){
        return  new ResponseEntity<>( this.priceService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> add(@RequestBody PriceRequest price){
        this.priceService.add(price);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
