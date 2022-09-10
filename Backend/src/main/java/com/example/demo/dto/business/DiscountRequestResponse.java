package com.example.demo.dto.business;

public class DiscountRequestResponse {
    public int id;
    public String discount;
    public String discountProof;
    public int userId;
    public String userName;
    public String userSurname;

    public  DiscountRequestResponse(){}

    public DiscountRequestResponse(int id, String discount, String discountProof, int useId, String userName, String userSurname) {
        this.id = id;
        this.discount = discount;
        this.discountProof = discountProof;
        this.userId = useId;
        this.userName = userName;
        this.userSurname = userSurname;
    }
}
