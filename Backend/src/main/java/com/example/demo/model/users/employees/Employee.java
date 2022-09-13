package com.example.demo.model.users.employees;

import com.example.demo.enums.EmployeeType;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {
    @Column
    private EmployeeType employeeType;
    @Column
    private double salary;

    public Employee() {}
    public Employee(int id, String name, String surname, String email, String telephone, Address address, String picture, Role role,EmployeeType employeeType, double salary) {
        super(id, name, surname, email, telephone, address, picture, role);
        this.employeeType = employeeType;
        this.salary = salary;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
