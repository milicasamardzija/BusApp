package com.example.demo.controller.business;

import com.example.demo.dto.business.DiscountNewRequest;
import com.example.demo.dto.business.DiscountRequestResponse;
import com.example.demo.model.business.DiscountRequest;
import com.example.demo.model.users.User;
import com.example.demo.service.business.DiscountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DiscountRequestController {

    @Autowired
    private DiscountRequestService discountRequestService;

    @GetMapping
    public ResponseEntity<List<DiscountRequestResponse>> getALl(){
        List<DiscountRequestResponse> ret = new ArrayList<>();
        for (DiscountRequest discountRequest: this.discountRequestService.getAll()
             ) {
            ret.add(new DiscountRequestResponse(discountRequest.getId(), discountRequest.getDiscount(), discountRequest.getDiscountProof(), discountRequest.getUser().getId(), discountRequest.getUser().getName(), discountRequest.getUser().getSurname()));
        }
        
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save (@RequestBody DiscountNewRequest discountNewRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        this.discountRequestService.save(discountNewRequest, user);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(value = "/approve")
    public ResponseEntity<HttpStatus> approve (@RequestBody DiscountRequestResponse discountRequestResponse){
        this.discountRequestService.approve(discountRequestResponse);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(value = "/reject")
    public ResponseEntity<HttpStatus> reject (@RequestBody DiscountRequestResponse discountRequestResponse){
        this.discountRequestService.reject(discountRequestResponse);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
