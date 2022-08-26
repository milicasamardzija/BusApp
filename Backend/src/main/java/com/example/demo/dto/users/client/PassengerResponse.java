package com.example.demo.dto.users.client;

public class PassengerResponse {

    public String name;
    public String surname;
    public String telephone;
    public String email;

    public PassengerResponse(String name, String surname, String telephone, String email) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
    }
}
