package com.example.demo.service.business;

import com.example.demo.dto.business.DiscountRequest;
import com.example.demo.enums.DiscountType;
import com.example.demo.model.business.Discount;
import com.example.demo.repository.busineess.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public void save(DiscountRequest discount){
        Discount discountNew = new Discount();
        discountNew.setDiscountType(discount.discountType);
        discountNew.setPercentage(discount.percentage);
        this.discountRepository.save(discountNew);
    }

    public void change(Discount discount){
        Discount discountExist = this.discountRepository.findById(discount.getId());
        discountExist.setDiscountType(discount.getDiscountType());
        discountExist.setPercentage(discount.getPercentage());
        this.discountRepository.save(discountExist);
    }

    public void delete(int id){
        this.discountRepository.deleteById(id);
    }

    public List<Discount> getAll(){
        return this.discountRepository.findAll();
    }

    public Discount getById(int id) {
        return this.discountRepository.findById(id);
    }

    public Discount getByTypeWithPassengers(DiscountType discount) {
        return this.discountRepository.getByTypeWithPassengers(discount);
    }

    public void update(Discount discount) {
        this.discountRepository.save(discount);
    }
}
