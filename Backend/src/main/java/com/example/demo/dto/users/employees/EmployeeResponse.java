package com.example.demo.dto.users.employees;

public class EmployeeResponse {

    public  int id;
    public String name;
    public String surname;
    public String telephone;
    public String email;
    public double salary;
    public String type;

    public EmployeeResponse(int id, String name, String surname, String telephone, String email, double salary, String type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.salary = salary;
        this.type = type;
    }
}
