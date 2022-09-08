package com.example.demo.dto.users;

public class UserDeleteRequestResponse {
    public int id;
    public String explanation;
    public String name;
    public String surname;
    public String email;

    public UserDeleteRequestResponse(int id, String explanation, String name, String surname, String email) {
        this.id = id;
        this.explanation = explanation;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
