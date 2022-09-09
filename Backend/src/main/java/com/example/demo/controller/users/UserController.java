package com.example.demo.controller.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.dto.users.UserResponse;
import com.example.demo.model.users.User;
import com.example.demo.model.users.UserRegistrationRequest;
import com.example.demo.service.users.UserRegistrationRequestService;
import com.example.demo.service.users.UserService;
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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRegistrationRequestService userRegistrationRequestService;

    @GetMapping(value = "")
    public ResponseEntity<UserRequest> fetchProfileInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(new UserRequest(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserRequest updatedUser){
        userService.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> ret = new ArrayList<>();

        for (User user: this.userService.getAll()) {
            ret.add(new UserResponse(user));
        }
        
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/requests")
    public ResponseEntity<List<UserRegistrationRequest>> getRequests(){
        return new ResponseEntity<>(this.userRegistrationRequestService.getAll(), HttpStatus.OK);
    }
}
