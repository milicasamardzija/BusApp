package com.example.demo.controller.users.employees;

import com.example.demo.dto.users.employees.EmployeeRequest;
import com.example.demo.dto.users.employees.EmployeeResponse;
import com.example.demo.model.users.employees.Employee;
import com.example.demo.service.users.employees.BusControllerService;
import com.example.demo.service.users.employees.BusDriverService;
import com.example.demo.service.users.employees.EmployeeService;
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
    @Autowired
    private BusDriverService busDriverService;
    @Autowired
    private BusControllerService busControllerService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        List<EmployeeResponse> ret = new ArrayList<>();
        for (Employee employee : this.employeeService.getAll()
        ) {
            String type = this.employeeService.getEmployeeType(employee.getId(), employee.getEmployeeType());
            if (type.equals("Sluzbenik")){
                break;
            }
            ret.add(new EmployeeResponse(employee.getId(),employee.getName(), employee.getSurname(), employee.getTelephone(), employee.getEmail(), employee.getSalary(), type, employee.getAddress()));
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable int id){
        Employee employee = this.employeeService.getById(id);
        String type = this.employeeService.getEmployeeType(employee.getId(), employee.getEmployeeType());
        return new ResponseEntity<>(new EmployeeResponse(employee.getId(),employee.getName(), employee.getSurname(), employee.getTelephone(), employee.getEmail(), employee.getSalary(), type, employee.getAddress()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addEmployee(@RequestBody EmployeeRequest employee){
        this.employeeService.saveEmployee(employee);
        return new ResponseEntity<>( HttpStatus.CREATED);
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
