package com.example.demo.controller.users.employees;

import com.example.demo.service.users.employees.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> confirm(@PathVariable int id) throws InterruptedException {
        staffService.addStaffMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
