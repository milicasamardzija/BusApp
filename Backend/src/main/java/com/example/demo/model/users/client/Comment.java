package com.example.demo.model.users.client;

import com.example.demo.model.users.User;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @SequenceGenerator(name = "commentSeqGen", sequenceName = "commentSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column
    private String text;
    @Column
    private Boolean accepted;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String text, User user, Boolean accepted) {
        this.text = text;
        this.accepted = accepted;
        this.user = user;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
