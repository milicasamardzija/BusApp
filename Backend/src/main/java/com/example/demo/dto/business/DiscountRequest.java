package com.example.demo.dto.business;


public class DiscountRequest {

    public int id;
    public String discountType;
    public double percentage;

    public DiscountRequest(){}

    public DiscountRequest(int id, String discountType, double percentage) {
        this.id = id;
        this.discountType = discountType;
        this.percentage = percentage;
    }
}
