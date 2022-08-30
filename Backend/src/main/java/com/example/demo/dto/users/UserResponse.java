package com.example.demo.dto.users;

import com.example.demo.model.users.Address;
import com.example.demo.model.users.User;

public class UserResponse {
    public String name;
    public String surname;
    public String email;
    public String telephone;
    public Address address;
    public String image;

    public UserResponse(User user) {
        name = user.getName();
        surname = user.getSurname();
        email = user.getEmail();
        telephone = user.getTelephone();
        address = user.getAddress();
        image = user.getPicture();
    }
}
