package com.example.demo.model.users.employees;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BUSS_CONTROLLER")
public class BusController extends BussStaff{
    public BusController(){}
}
