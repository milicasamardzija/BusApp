package com.example.demo.controller.business;

import com.example.demo.dto.business.PriceRequest;
import com.example.demo.service.business.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping
    public ResponseEntity<HttpStatus> add(@RequestBody PriceRequest price){
        this.priceService.add(price);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
