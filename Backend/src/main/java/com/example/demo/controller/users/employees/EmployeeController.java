package com.example.demo.controller.users.employees;

import com.example.demo.dto.users.client.PassengerResponse;
import com.example.demo.dto.users.employees.EmployeeRequest;
import com.example.demo.dto.users.employees.EmployeeResponse;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.model.users.employees.Employee;
import com.example.demo.service.users.employees.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        List<EmployeeResponse> ret = new ArrayList<>();
        for (Employee employee : this.employeeService.getAll()
        ) {
            ret.add(new EmployeeResponse(employee.getName(), employee.getSurname(), employee.getTelephone(), employee.getEmail(), employee.getSalary()));
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> changeEmployee(@RequestBody EmployeeRequest employee){
        this.employeeService.changeEmployee(employee);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        this.employeeService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
