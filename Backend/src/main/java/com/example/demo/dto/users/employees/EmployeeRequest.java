package com.example.demo.dto.users.employees;

import com.example.demo.enums.EmployeeType;
import com.example.demo.model.users.Address;

public class EmployeeRequest {

    public int id;
    public String name;
    public String surname;
    public String email;
    public Address address;
    public double salary;
    public EmployeeType employeeType;
    public String telephone;

}
