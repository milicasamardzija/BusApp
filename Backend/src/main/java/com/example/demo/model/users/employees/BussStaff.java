package com.example.demo.model.users.employees;

import com.example.demo.enums.BussStaffType;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BUSS_STAFF")
public class BussStaff extends Employee{

    @Column
    private BussStaffType bussStaffType;

    public BussStaff(){}

    public BussStaffType getBussStaffType() {
        return bussStaffType;
    }

    public void setBussStaffType(BussStaffType bussStaffType) {
        this.bussStaffType = bussStaffType;
    }
}
