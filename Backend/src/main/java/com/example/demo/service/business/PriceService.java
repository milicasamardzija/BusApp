package com.example.demo.service.business;

import com.example.demo.dto.business.PriceRequest;
import com.example.demo.model.business.Price;
import com.example.demo.repository.busineess.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public void add(PriceRequest priceRequest) {
        Price price = this.priceRepository.findById(priceRequest.id);
        price.setPricePerKilometer(priceRequest.pricePerKilometer);
        price.setPricePerKilometerMonthlyTicket(priceRequest.pricePerKilometerMonthlyTicket);
        this.priceRepository.save(price);
    }

    public Price getById(int id) {
        return this.priceRepository.findById(id);
    }
}
