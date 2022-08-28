package com.example.demo.model.users.employees;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BUSS_STAFF")
public class BussStaff extends Employee{

}
