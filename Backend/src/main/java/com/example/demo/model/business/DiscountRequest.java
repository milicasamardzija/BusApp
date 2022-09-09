package com.example.demo.model.business;

import com.example.demo.enums.DiscountType;
import com.example.demo.model.users.User;

import javax.persistence.*;

@Entity
public class DiscountRequest {
    @Id
    @SequenceGenerator(name = "discountReqSeqGen", sequenceName = "discountReqSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discountReqSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private String discount;
    @Column
    private String discountProof;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountProof() {
        return discountProof;
    }

    public void setDiscountProof(String discountProof) {
        this.discountProof = discountProof;
    }
}
