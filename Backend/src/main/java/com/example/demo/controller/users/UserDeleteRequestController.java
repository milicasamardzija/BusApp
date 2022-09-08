package com.example.demo.controller.users;

import com.example.demo.dto.users.UserDeleteRequestReq;
import com.example.demo.dto.users.UserDeleteRequestResponse;
import com.example.demo.model.users.User;
import com.example.demo.model.users.UserDeleteRequest;
import com.example.demo.service.users.UserDeleteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "userDeleteReq")
public class UserDeleteRequestController {

    @Autowired
    private UserDeleteRequestService userDeleteRequestService;

    @GetMapping
    public ResponseEntity<List<UserDeleteRequestResponse>> getAll(){
        List<UserDeleteRequestResponse> ret = new ArrayList<>();

        for (UserDeleteRequest userDeleteRequest: this.userDeleteRequestService.getAll()
             ) {
            ret.add(new UserDeleteRequestResponse(userDeleteRequest.getId(), userDeleteRequest.getExplanation(), userDeleteRequest.getUser().getName(), userDeleteRequest.getUser().getSurname(), userDeleteRequest.getUser().getEmail()));
        }
        
        return new ResponseEntity(ret, HttpStatus.OK);
    }

    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<HttpStatus> accept(@PathVariable int id){
        this.userDeleteRequestService.acceptRequest(id);
        return new ResponseEntity( HttpStatus.OK);
    }

    @PutMapping(value = "/reject/{id}")
    public ResponseEntity<HttpStatus> reject(@PathVariable int id){
        this.userDeleteRequestService.rejectRequest(id);
        return new ResponseEntity( HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> add(@RequestBody UserDeleteRequestReq userDeleteRequestReq){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        this.userDeleteRequestService.saveRequest(new UserDeleteRequest(userDeleteRequestReq.explanation, user));
        return new ResponseEntity( HttpStatus.OK);
    }
}
