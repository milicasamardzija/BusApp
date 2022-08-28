package com.example.demo.controller.users;

import com.example.demo.dto.users.auth.JwtAuthenticationRequest;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.dto.users.auth.UserTokenState;
import com.example.demo.model.users.*;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.users.*;
import com.example.demo.service.users.client.PassengerRegistrationTokenService;
import com.example.demo.service.users.client.PassengerService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRegistrationRequestService userRegistrationRequestService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private PassengerRegistrationTokenService passengerRegistrationTokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.email, authenticationRequest.password));
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();
        if (!user.isEnabled()) {
            return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,user.getRole().getName(), user.isEnabled()));
        }

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,user.getRole().getName(), user.isEnabled()));
    }


    @RequestMapping(value="/signup", method = {RequestMethod.POST })
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserRequest userRequest){

       User existUser = this.userService.findByEmail(userRequest.email);
       if (existUser != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       userRegistrationRequestService.save(userRequest);
       if (userRequest.role.equals("ROLE_PASSENGER")){
           String code = passengerRegistrationTokenService.save(userRequest.email);
           emailSenderService.sendEmailForVerification(userRequest.email, code);
       }
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "confirm-account/{code}")
    public ResponseEntity<HttpStatus> confirm(@PathVariable String code) throws URISyntaxException {
        String email = this.passengerRegistrationTokenService.findByCode(code);
        UserRegistrationRequest user = this.userRegistrationRequestService.findByEmail(email);
        this.passengerService.savePassenger(user);
        this.userRegistrationRequestService.deleteByEmail(email);

        URI frontend = new URI("http://localhost:4200/");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(frontend);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

}
