package com.example.demo.service.tickets;

import com.example.demo.dto.tickets.StandardTicketRequest;
import com.example.demo.enums.TicketType;
import com.example.demo.model.tickets.StandardTicket;
import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.repository.tickets.StandardTicketRepository;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.pdf.PdfGenerateService;
import com.example.demo.service.pdf.QRCodeGenerator;
import com.example.demo.service.users.client.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class StandardTicketService {

    @Autowired
    private StandardTicketRepository standardTicketRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private PdfGenerateService pdfGenerateService;
    @Autowired
    private QRCodeGenerator QRCodeGenerator;
    @Autowired
    private EmailSenderService emailSenderService;

    public List<StandardTicket> findAll() {
        return this.standardTicketRepository.findAll();
    }

    public void addTicket(StandardTicketRequest ticket, User user) {
        Passenger passenger = this.passengerService.findByIdWithTickets(user.getId());

        StandardTicket standardTicket = new StandardTicket();
        standardTicket.setTicketType(TicketType.STANDARDNA_KARTA);
        standardTicket.setCityStart(ticket.cityStart);
        standardTicket.setCityEnd(ticket.cityEnd);
        standardTicket.setTimeStart(ticket.timeStart);
        standardTicket.setDateIssued(new Date());

        Calendar dateEnd = Calendar.getInstance();
        dateEnd.set(Calendar.DAY_OF_MONTH, 30);
        dateEnd.set(Calendar.MONTH, standardTicket.getDateIssued().getMonth() + 1);
        standardTicket.setDateExpiration(dateEnd.getTime());

        standardTicket.setDateChecked(null);
        standardTicket.setPrice(ticket.price);
        standardTicket.setPassenger(passenger);
        this.standardTicketRepository.save(standardTicket);

        passenger.getTickets().add(standardTicket);
        this.passengerService.update(passenger);

        this.QRCodeGenerator.getQrCode(standardTicket.getId());
        this.generatePdf(passenger, standardTicket);
        this.emailSenderService.sendEmailWithPdf(passenger);
    }

    private void generatePdf(Passenger passenger, StandardTicket standardTicket) {
        Map<String, Object> data = new HashMap<>();
        data.put("passenger", passenger);
        data.put("standardTicket", standardTicket);
        pdfGenerateService.generatePdfFile("ticketTemplate", data, "karta.pdf");
    }

    public List<StandardTicket> getPreviousTickets(User user) {
        return this.standardTicketRepository.getPreviousTickets(user.getId(), new Date());
    }
}
