package com.example.demo.service.users.client;

import com.example.demo.repository.users.client.PassengerRepository;
import com.example.demo.repository.users.RoleRepository;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.UserRegistrationRequest;
import com.example.demo.service.users.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddressService addressService;

    public void savePassenger(UserRegistrationRequest user) {
        Passenger passenger = new Passenger();
        Role role = this.roleRepository.findByName("ROLE_PASSENGER");
        passenger.setName(user.getName());
        passenger.setSurname(user.getSurname());
        passenger.setPassword(passwordEncoder.encode(user.getPassword()));
        passenger.setAddress(addressService.save(new Address(user.getCountry(), user.getCity(), user.getStreet(), user.getNumber())));
        passenger.setEmail(user.getEmail());
        passenger.setEnabled(true);
        passenger.setTelephone(user.getTelephone());
        passenger.setRole(role);
        this.passengerRepository.save(passenger);
    }

    public List<Passenger> getAll(){
        return this.passengerRepository.findAll();
    }

    public Passenger findById(int id) {
        return this.passengerRepository.findById(id);
    }

    public Passenger findByIdWithTickets(int id) {
        return this.passengerRepository.findByIdWithTickets(id);
    }

    public void update(Passenger passenger) {
        this.passengerRepository.save(passenger);
    }

    public void save(Passenger passenger) {
        this.passengerRepository.save(passenger);
    }
}
