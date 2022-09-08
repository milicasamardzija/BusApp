package com.example.demo.service.business;

import com.example.demo.dto.business.PriceRequest;
import com.example.demo.model.business.Price;
import com.example.demo.repository.busineess.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getAll(){
        return this.priceRepository.findAll();
    }

    public void add(PriceRequest priceRequest) {
        Price price = new Price();
        price.setPricePerKilometer(priceRequest.pricePerKilometer);
        price.setPricePerKilometerMonthlyTicket(priceRequest.pricePerKilometerMonthlyTicket);
        price.setDateStart(priceRequest.dateStart);
        price.setDateEnd(priceRequest.dateEnd);
        this.priceRepository.save(price);
    }

    public Price getById(int id) {
        return this.priceRepository.findById(id);
    }

    public Price getValidPrice() {
        return this.priceRepository.getValidPrice();
    }
}
