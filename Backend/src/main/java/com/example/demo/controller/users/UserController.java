package com.example.demo.controller.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.dto.users.UserResponse;
import com.example.demo.model.users.User;
import com.example.demo.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<UserResponse> fetchProfileInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserRequest updatedUser){
        userService.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
