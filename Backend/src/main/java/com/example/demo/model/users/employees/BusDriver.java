package com.example.demo.model.users.employees;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BUSS_DRIVER")
public class BusDriver extends BussStaff {
    public BusDriver(){}
}
