package com.example.demo.service.tickets;

import com.example.demo.dto.tickets.MonthlyTicketRequest;
import com.example.demo.dto.tickets.TicketCheckResponse;
import com.example.demo.dto.tickets.TicketPdfResponse;
import com.example.demo.enums.TicketType;
import com.example.demo.model.business.ActiveDeparture;
import com.example.demo.model.tickets.MonthlyTicket;
import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.repository.tickets.MonthlyTicketRepository;
import com.example.demo.service.business.ActiveDepartureService;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.pdf.PdfGenerateService;
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
    @Autowired
    private ActiveDepartureService activeDepartureService;

    public MonthlyTicket getById(int idTicket) {
        return this.monthlyTicketRepository.findById(idTicket);
    }

    public void addTicket(MonthlyTicketRequest ticket, User user) {
        Passenger passenger = this.passengerService.findByIdWithTickets(user.getId());

        ActiveDeparture activeDeparture = this.activeDepartureService.getById(ticket.activeDepartureId);
        activeDeparture.setSeats(activeDeparture.getSeats() - 1);
        this.activeDepartureService.update(activeDeparture);

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

        monthlyTicket.setDiscountPercentage(ticket.discountPercentage);

        this.monthlyTicketRepository.save(monthlyTicket);

        passenger.getTickets().add(monthlyTicket);
        this.passengerService.update(passenger);
    }

    private void generatePdf(Passenger passenger, TicketPdfResponse standardTicket) {
        Map<String, Object> data = new HashMap<>();
        data.put("passenger", passenger);
        data.put("standardTicket", standardTicket);
        pdfGenerateService.generatePdfFile("ticketTemplate", data, "karta.pdf");
    }

    public List<MonthlyTicket> getMonthlyTicket(User user) {
        return this.monthlyTicketRepository.getMonthlyTicketByUser(user.getId(), new Date());
    }

    public List<MonthlyTicket> getRequests() {
        return this.monthlyTicketRepository.getRequests();
    }

    public MonthlyTicket monthlyTicketApprove(int id) {
        MonthlyTicket monthlyTicket = this.monthlyTicketRepository.findById(id);
        monthlyTicket.setApproved(true);
        this.monthlyTicketRepository.save(monthlyTicket);
        this.QRCodeGenerator.getQrCodeForMonthlyTicket(monthlyTicket.getId());
        this.generatePdf(monthlyTicket.getPassenger(), new TicketPdfResponse(monthlyTicket));
        this.emailSenderService.sendEmailWithPdf(monthlyTicket.getPassenger());
        return monthlyTicket;
    }

    public MonthlyTicket getMonthlyTicketById(int id) {
        return this.monthlyTicketRepository.findById(id);
    }

    public List<MonthlyTicket> previousTickets(User user) {
        return this.monthlyTicketRepository.previousTickets(user.getId(), new Date());
    }

    public void sendTicketToMail(int id) {
        MonthlyTicket monthlyTicket = this.monthlyTicketRepository.findById(id);
        this.QRCodeGenerator.getQrCodeForMonthlyTicket(monthlyTicket.getId());
        this.generatePdf(monthlyTicket.getPassenger(), new TicketPdfResponse(monthlyTicket));
        this.emailSenderService.sendEmailWithPdf(monthlyTicket.getPassenger());
    }

    public TicketCheckResponse checkTicket(int id) {
        MonthlyTicket monthlyTicket = this.monthlyTicketRepository.findById(id);
        TicketCheckResponse ticketCheckResponse = new TicketCheckResponse();
        ticketCheckResponse.cityEnd = monthlyTicket.getCityEnd();
        ticketCheckResponse.cityStart = monthlyTicket.getCityStart();
        ticketCheckResponse.name = monthlyTicket.getPassenger().getName();
        ticketCheckResponse.surname = monthlyTicket.getPassenger().getSurname();

        if (monthlyTicket.getApproved() && monthlyTicket.getDateExpiration().after(new Date())){
            ticketCheckResponse.response = "Mesecna karta je uspesno verifikovana!";
            return ticketCheckResponse;
        } else if (monthlyTicket.getDateExpiration().before(new Date())){
            ticketCheckResponse.response = "Rok vazenja mesecne karte je istekao!";
            return ticketCheckResponse;
        }

        return ticketCheckResponse;
    }

    public void monthlyTicketReject(int id) {
        MonthlyTicket monthlyTicket = this.monthlyTicketRepository.findById(id);
        this.emailSenderService.sendMonthlyTicketRejection(monthlyTicket.getPassenger());
        this.monthlyTicketRepository.delete(monthlyTicket);
    }
}
