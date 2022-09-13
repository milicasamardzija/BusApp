package com.example.demo.model.tickets;

import com.example.demo.enums.StandardTicketType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("ST")
public class StandardTicket extends Ticket{
    @Column
    private Date dateChecked;
    @Column
    private int returnChecks;
    @Column
    private StandardTicketType standardTicketType;
    @Column
    private Date date;

    public int getReturnChecks() {
        return returnChecks;
    }

    public void setReturnChecks(int returnChecks) {
        this.returnChecks = returnChecks;
    }

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
