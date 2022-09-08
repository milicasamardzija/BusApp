package com.example.demo.dto.tickets;

public class CheckTicketResponse {

    public String text;

    public CheckTicketResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
