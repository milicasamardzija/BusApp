package com.example.demo.controller.business;

import com.example.demo.model.business.Discount;
import com.example.demo.service.business.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<Discount>> getAll(){
        return new ResponseEntity<>(this.discountService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Discount> getById(@PathVariable int id){
        return new ResponseEntity<>(this.discountService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody Discount discount){
        this.discountService.save(discount);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> change(@RequestBody Discount discount){
        this.discountService.change(discount);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        this.discountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
