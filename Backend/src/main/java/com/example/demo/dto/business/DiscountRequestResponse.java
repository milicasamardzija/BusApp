package com.example.demo.dto.business;

import com.example.demo.enums.DiscountType;

public class DiscountRequestResponse {
    public int id;
    public DiscountType discount;
    public String discountProof;
    public int userId;
    public String userName;
    public String userSurname;

    public DiscountRequestResponse(int id, DiscountType discount, String discountProof, int useId, String userName, String userSurname) {
        this.id = id;
        this.discount = discount;
        this.discountProof = discountProof;
        this.userId = useId;
        this.userName = userName;
        this.userSurname = userSurname;
    }
}
