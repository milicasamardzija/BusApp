package com.example.demo.service.business;

import com.example.demo.dto.business.DiscountNewRequest;
import com.example.demo.dto.business.DiscountRequestResponse;
import com.example.demo.model.business.Discount;
import com.example.demo.model.business.DiscountRequest;
import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.repository.busineess.DiscountRequestRepository;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.users.client.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountRequestService {

    @Autowired
    private DiscountRequestRepository discountRequestRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private EmailSenderService emailSenderService;

    public List<DiscountRequest> getAll(){
        return this.discountRequestRepository.findAll();
    }

    //dodaj novi
    public void save(DiscountNewRequest newRequest, User user){
        DiscountRequest discountRequest = new DiscountRequest();
        discountRequest.setDiscount(newRequest.discount);
        discountRequest.setDiscountProof(newRequest.discountProof);
        discountRequest.setUser(user);
        this.discountRequestRepository.save(discountRequest);
    }

    //odobri
    public void approve(DiscountRequestResponse discountRequestResponse){
        Passenger passenger = this.passengerService.findById((discountRequestResponse.id));
        Discount discount = this.discountService.getByTypeWithPassengers(discountRequestResponse.discount);
        passenger.setDiscount(discount);
        passengerService.save(passenger);

        List<Passenger> passengers = discount.getPassengers();
        passengers.add(passenger);
        discount.setPassengers(passengers);
        this.discountService.update(discount);

        this.discountRequestRepository.deleteById(discountRequestResponse.id);
    }

    //odbij
    public void reject(DiscountRequestResponse discountRequestResponse){
        Passenger passenger = this.passengerService.findById((discountRequestResponse.id));

        this.emailSenderService.sendEmailForDiscountRejection(passenger.getEmail());
        this.discountRequestRepository.deleteById(discountRequestResponse.id);
    }
}
