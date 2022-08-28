package com.example.demo.service.tickets;

import com.example.demo.dto.tickets.MonthlyTicketRequest;
import com.example.demo.enums.TicketType;
import com.example.demo.model.tickets.MonthlyTicket;
import com.example.demo.model.tickets.StandardTicket;
import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.repository.tickets.MonthlyTicketRepository;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.pdf.PdfGenerateService;
import com.example.demo.service.pdf.QRCodeGenerator;
import com.example.demo.service.users.client.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonthlyTicketService {

    @Autowired
    private MonthlyTicketRepository monthlyTicketRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private PdfGenerateService pdfGenerateService;
    @Autowired
    private com.example.demo.service.pdf.QRCodeGenerator QRCodeGenerator;
    @Autowired
    private EmailSenderService emailSenderService;

    public MonthlyTicket getById(int idTicket) {
        return this.monthlyTicketRepository.findById(idTicket);
    }

    public void addTicket(MonthlyTicketRequest ticket, User user) {
        Passenger passenger = this.passengerService.findByIdWithTickets(user.getId());

        MonthlyTicket monthlyTicket = new MonthlyTicket();
        monthlyTicket.setTicketType(TicketType.MESECNA_KARTA);
        monthlyTicket.setCityStart(ticket.cityStart);
        monthlyTicket.setCityEnd(ticket.cityEnd);
        monthlyTicket.setApproved(false);
        monthlyTicket.setPrice(ticket.price);
        monthlyTicket.setPassenger(passenger);

        Calendar dateStart = Calendar.getInstance();
        dateStart.set(Calendar.DAY_OF_MONTH, 1);
        dateStart.set(Calendar.MONTH, new Date().getMonth() + 1);
        monthlyTicket.setDateIssued(dateStart.getTime());

        Calendar dateEnd = Calendar.getInstance();
        dateEnd.set(Calendar.DAY_OF_MONTH, 30);
        dateEnd.set(Calendar.MONTH, new Date().getMonth() + 1);
        monthlyTicket.setDateExpiration(dateEnd.getTime());

        passenger.getTickets().add(monthlyTicket);
        this.passengerService.update(passenger);
    }

    private void generatePdf(Passenger passenger, MonthlyTicket standardTicket) {
        Map<String, Object> data = new HashMap<>();
        data.put("passenger", passenger);
        data.put("standardTicket", standardTicket);
        pdfGenerateService.generatePdfFile("ticketTemplate", data, "ticket.pdf");
    }

    public MonthlyTicket getMonthlyTicket(User user) {
        return this.monthlyTicketRepository.getMonthlyTicketByUser(user.getId());
    }

    public List<MonthlyTicket> getRequests() {
        return this.monthlyTicketRepository.getRequests();
    }

    public MonthlyTicket monthlyTicketApprove(int id) {
        MonthlyTicket monthlyTicket = this.monthlyTicketRepository.findById(id);
        monthlyTicket.setApproved(true);
        this.monthlyTicketRepository.save(monthlyTicket);
        this.QRCodeGenerator.getQrCode(monthlyTicket.getId());
        this.generatePdf(monthlyTicket.getPassenger(), monthlyTicket);
        this.emailSenderService.sendEmailWithPdf(monthlyTicket.getPassenger());
        return monthlyTicket;
    }
}
