package com.example.demo.dto.users.client;

public class CommentResponse {

    public int id;
    public String text;
    public Boolean accepted;
    public String name;
    public String surname;
    public String image;

    public CommentResponse(int id, String text, Boolean accepted, String name, String surname, String image) {
        this.id = id;
        this.text = text;
        this.accepted = accepted;
        this.name = name;
        this.surname = surname;
        this.image = image;
    }
}
