package com.example.demo.dto.users.employees;

public class EmployeeResponse {

    public String name;
    public String surname;
    public String telephone;
    public String email;
    public double salary;

    public EmployeeResponse(String name, String surname, String telephone, String email, double salary) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.salary = salary;
    }
}
