package com.example.demo.dto.users;

import com.example.demo.model.users.User;

public class UserRequest {

    public String password;
    public String name;
    public String surname;
    public String email;
    public String role;
    public String telephone;
    public String country;
    public String city;
    public String street;
    public String number;
    public String image;

    public UserRequest() {}

    public UserRequest(User user) {
        name = user.getName();
        surname = user.getSurname();
        email = user.getEmail();
        telephone = user.getTelephone();
        country = user.getAddress().getCountry();
        city = user.getAddress().getCity();
        street = user.getAddress().getStreet();
        number = user.getAddress().getNumber();
        image = user.getPicture();
        password = user.getPassword();
    }
}
