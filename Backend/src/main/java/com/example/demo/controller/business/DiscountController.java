package com.example.demo.controller.business;

import com.example.demo.dto.business.DiscountRequest;
import com.example.demo.model.business.Discount;
import com.example.demo.service.business.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<DiscountRequest>> getAll(){
        List<DiscountRequest> ret = new ArrayList<>();
        for (Discount discount: this.discountService.getAll()
             ) {
            ret.add(new DiscountRequest(discount.getId(), discount.getDiscountType(), discount.getPercentage()));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DiscountRequest> getById(@PathVariable int id){
        Discount discount = this.discountService.getById(id);
        return new ResponseEntity<>(new DiscountRequest(discount.getId(), discount.getDiscountType(), discount.getPercentage()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody DiscountRequest discount){
        this.discountService.save(discount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> change(@RequestBody DiscountRequest discount){
        this.discountService.change(new Discount(discount.id, discount.discountType, discount.percentage));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        this.discountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
