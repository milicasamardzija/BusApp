package com.example.demo.model.users.client;

import com.example.demo.model.users.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PASSENGER")
public class Passenger extends User {
}
