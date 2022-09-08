package com.example.demo.model.tickets;

import com.example.demo.enums.StandardTicketType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("ST")
public class StandardTicket extends Ticket{
    @Column
    private Date dateChecked;
    @Column
    private StandardTicketType standardTicketType;
    @Column
    private Date date;


    public Date getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        this.dateChecked = dateChecked;
    }

    public StandardTicketType getStandardTicketType() {
        return standardTicketType;
    }

    public void setStandardTicketType(StandardTicketType standardTicketType) {
        this.standardTicketType = standardTicketType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
