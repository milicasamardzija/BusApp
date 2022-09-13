package com.example.demo.service.tickets;

import com.example.demo.dto.tickets.StandardTicketRequest;
import com.example.demo.dto.tickets.TicketCheckResponse;
import com.example.demo.dto.tickets.TicketPdfResponse;
import com.example.demo.enums.StandardTicketType;
import com.example.demo.enums.TicketType;
import com.example.demo.model.business.ActiveDeparture;
import com.example.demo.model.tickets.StandardTicket;
import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.repository.tickets.StandardTicketRepository;
import com.example.demo.service.business.ActiveDepartureService;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.pdf.PdfGenerateService;
import com.example.demo.service.pdf.QRCodeGenerator;
import com.example.demo.service.users.client.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
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
    @Autowired
    private ActiveDepartureService activeDepartureService;

    public List<StandardTicket> findAll() {
        return this.standardTicketRepository.findAll();
    }

    public void addTicket(StandardTicketRequest ticket, User user) throws ParseException {
        Passenger passenger = this.passengerService.findByIdWithTickets(user.getId());

        ActiveDeparture activeDeparture = this.activeDepartureService.getById(ticket.activeDepartureId);
        activeDeparture.setSeats(activeDeparture.getSeats() - 1);
        this.activeDepartureService.update(activeDeparture);

        StandardTicket standardTicket = new StandardTicket();
        standardTicket.setTicketType(TicketType.STANDARDNA_KARTA);
        standardTicket.setCityStart(ticket.cityStart);
        standardTicket.setCityEnd(ticket.cityEnd);
        standardTicket.setTimeStart(ticket.timeStart);
        standardTicket.setStandardTicketType(ticket.standardTicketType);
        standardTicket.setDate(ticket.date);

        standardTicket.setDateIssued(new Date());
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.set(Calendar.DAY_OF_MONTH, 30);
        dateEnd.set(Calendar.MONTH, standardTicket.getDateIssued().getMonth() + 1);

        if (standardTicket.getStandardTicketType() == StandardTicketType.Povratna)
            standardTicket.setDateExpiration(dateEnd.getTime());

        standardTicket.setDateChecked(null);
        standardTicket.setPrice(ticket.price);
        standardTicket.setPassenger(passenger);
        standardTicket.setFullPrice(ticket.fullPrice);
        standardTicket.setDiscountPercentage(ticket.discountPercentage);

        StandardTicket t = this.standardTicketRepository.save(standardTicket);

        passenger.getTickets().add(standardTicket);
        this.passengerService.update(passenger);

        this.QRCodeGenerator.getQrCodeForStandardTicket(standardTicket.getId());
        if (standardTicket.getStandardTicketType() == StandardTicketType.Jednosmerna)
            this.generatePdf(passenger, new TicketPdfResponse(t), ticket.timeStart, activeDeparture.getDayOfWeek().toString());
        else
            this.generatePdfPovratna(passenger, new TicketPdfResponse(standardTicket), ticket.timeStart, activeDeparture.getDayOfWeek().toString());

        this.emailSenderService.sendEmailWithPdf(passenger);
    }

    private void generatePdfPovratna(Passenger passenger, TicketPdfResponse ticketPdfResponse, String timeStart, String days) {
        Map<String, Object> data = new HashMap<>();
        data.put("passenger", passenger);
        data.put("standardTicket", ticketPdfResponse);
        data.put("time", timeStart);
        data.put("days", days);
        pdfGenerateService.generatePdfFile("standardReturnTicketTemplate", data, "karta.pdf");
    }

    private void generatePdf(Passenger passenger, TicketPdfResponse standardTicket, String timeStart, String days) {
        Map<String, Object> data = new HashMap<>();
        data.put("passenger", passenger);
        data.put("standardTicket", standardTicket);
        data.put("time", timeStart);
        data.put("days", days);
        pdfGenerateService.generatePdfFile("standardTicketTemplate", data, "karta.pdf");
    }

    public List<StandardTicket> getPreviousTickets(User user) {
        return this.standardTicketRepository.getPreviousTickets(user.getId(), new Date());
    }

    public TicketCheckResponse checkTicket(int id) {
        StandardTicket standardTicket = this.standardTicketRepository.findById(id);
        TicketCheckResponse ticketCheckResponse = new TicketCheckResponse();
        ticketCheckResponse.typeTicket = standardTicket.getStandardTicketType().toString();
        ticketCheckResponse.cityEnd = standardTicket.getCityEnd();
        ticketCheckResponse.cityStart = standardTicket.getCityStart();
        ticketCheckResponse.name = standardTicket.getPassenger().getName();
        ticketCheckResponse.surname = standardTicket.getPassenger().getSurname();
        ticketCheckResponse.dateStart = standardTicket.getDate();

        if(standardTicket.getStandardTicketType() == StandardTicketType.Povratna) {
            if (standardTicket.getReturnChecks() <= 1 && standardTicket.getDateExpiration().after(new Date()) && standardTicket.getDate().getMonth() == new Date().getMonth() && standardTicket.getDate().getYear() == new Date().getYear()  && standardTicket.getDate().getDate() == new Date().getDate())  {
                standardTicket.setReturnChecks(standardTicket.getReturnChecks()+1);
                standardTicket.setDateChecked(new Date());
                this.standardTicketRepository.save(standardTicket);
                ticketCheckResponse.response = "Karta je uspesno verifikovana!";
                return ticketCheckResponse;
            } else if (standardTicket.getReturnChecks() >= 2 && standardTicket.getDateExpiration().after(new Date())) {
                ticketCheckResponse.response = "Karta je vec iskoriscena!";
                return ticketCheckResponse;
            } else if (standardTicket.getDateExpiration().before(new Date())) {
                ticketCheckResponse.response = "Rok vazenja ove karte je istekao!";
                return ticketCheckResponse;
            }
        } else {
            if (standardTicket.getReturnChecks() <= 0 && standardTicket.getDate().getDate() == new Date().getDate() && standardTicket.getDate().getMonth() == new Date().getMonth() && standardTicket.getDate().getYear() == new Date().getYear()) {
                standardTicket.setReturnChecks(standardTicket.getReturnChecks() + 1);
                standardTicket.setDateChecked(new Date());
                this.standardTicketRepository.save(standardTicket);
                ticketCheckResponse.response = "Karta je uspesno verifikovana!";
                return ticketCheckResponse;
            } else if (standardTicket.getReturnChecks() >= 1) {
                ticketCheckResponse.response = "Karta je vec iskoriscena!";
                return ticketCheckResponse;
            } else if (standardTicket.getDate().getMonth() != new Date().getMonth() || standardTicket.getDate().getYear() != new Date().getYear() || standardTicket.getDate().getDate() != new Date().getDate()) {
                ticketCheckResponse.response = "Karta ne vazi za danasnji datum!";
                return ticketCheckResponse;
            }
        }
        ticketCheckResponse.response = "";
        return ticketCheckResponse;
    }

    public void getDailyReport(User user) {
        List<StandardTicket> standardTickets = this.standardTicketRepository.findAll();
        List<TicketPdfResponse > tickets = new ArrayList<>();
        Date dateTodayStart = new Date();
        dateTodayStart.setHours(0);
        dateTodayStart.setMinutes(0);

        Date dateTodayEnd = new Date();
        dateTodayEnd.setHours(23);
        dateTodayEnd.setMinutes(59);

        for (StandardTicket standardTicket: standardTickets
             ) {
            if (standardTicket.getDateIssued().after(dateTodayStart) && standardTicket.getDateIssued().before(dateTodayEnd)){
                tickets.add(new TicketPdfResponse(standardTicket));
            }
        }
        
        double sum = this.getSum(tickets);
        this.generatePdfForDailyReport(tickets, sum);
        this.emailSenderService.sendEmailWithPdfForDailyReport(user);
    }

    private double getSum(List<TicketPdfResponse> tickets) {
        double sum = 0;
        for (TicketPdfResponse standardTicket: tickets
             ) {
            sum += standardTicket.price;
        }
        return sum;
    }

    private void generatePdfForDailyReport(List<TicketPdfResponse> standardTicket, double sum) {
        Map<String, Object> data = new HashMap<>();
        data.put("tickets", standardTicket);
        data.put("sum", sum);
        pdfGenerateService.generatePdfFile("reportTemplate", data, "dnevni_izvestaj.pdf");
    }
}
