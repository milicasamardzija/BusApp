package com.example.demo.service.users;

import com.example.demo.repository.users.UserRegistrationRequestRepository;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.users.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegistrationRequestService {

    @Autowired
    UserRegistrationRequestRepository userRegistrationRequestRepository;

    public UserRegistrationRequest findByEmail(String email) {
        return this.userRegistrationRequestRepository.findByEmail(email);
    }

    public void save(UserRequest userRequest) {
        this.userRegistrationRequestRepository.save(new UserRegistrationRequest(userRequest.email, userRequest.password, userRequest.name, userRequest.surname, userRequest.role, userRequest.telephone, userRequest.country, userRequest.city, userRequest.street, userRequest.number));
    }

    public List<UserRegistrationRequest> getAll(){
        return this.userRegistrationRequestRepository.findAll();
    }

    public void deleteByEmail(String email) {
        this.userRegistrationRequestRepository.delete(this.findByEmail(email));
    }

    public UserRegistrationRequest findById(int id) {
        return userRegistrationRequestRepository.findById(id);
    }
}
