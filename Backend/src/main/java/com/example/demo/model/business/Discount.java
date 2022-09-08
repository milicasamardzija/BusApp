package com.example.demo.model.business;

import com.example.demo.model.users.client.Passenger;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Discount {

    @Id
    @SequenceGenerator(name = "discountSeqGen", sequenceName = "discountSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discountSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private String discountType;
    @Column
    private double percentage;
    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Passenger> passengers;

    public Discount() {}

    public Discount(int id, String discountType, double percentage) {
        this.id = id;
        this.discountType = discountType;
        this.percentage = percentage;
    }

    public Discount(String discountType, double percentage) {
        this.discountType = discountType;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
