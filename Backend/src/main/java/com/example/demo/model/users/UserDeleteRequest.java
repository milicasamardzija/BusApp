package com.example.demo.model.users;

import javax.persistence.*;

@Entity
public class UserDeleteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column(name="explanation")
    private String explanation;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    public UserDeleteRequest() {
    }

    public UserDeleteRequest(int id, String explanation, User user) {
        this.id = id;
        this.explanation = explanation;
        this.user = user;
    }

    public UserDeleteRequest(String explanation, User user) {
        this.explanation = explanation;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
