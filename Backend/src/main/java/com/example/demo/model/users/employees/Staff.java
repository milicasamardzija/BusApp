package com.example.demo.model.users.employees;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STAFF")
public class Staff extends Employee {
}
