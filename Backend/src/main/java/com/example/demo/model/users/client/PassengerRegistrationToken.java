package com.example.demo.model.users.client;

import javax.persistence.*;

@Entity
public class PassengerRegistrationToken {
    @Id
    @SequenceGenerator(name = "passRegTokenSeqGen", sequenceName = "passRegTokenSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passRegTokenSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column(unique=true)
    private String email;
    @Column
    private String code;

    public PassengerRegistrationToken(){}

    public PassengerRegistrationToken(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
