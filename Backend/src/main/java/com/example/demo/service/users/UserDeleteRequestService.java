package com.example.demo.service.users;

import com.example.demo.model.users.UserDeleteRequest;
import com.example.demo.repository.users.UserDeleteRequestRepository;
import com.example.demo.service.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDeleteRequestService {

    @Autowired
    private UserDeleteRequestRepository userDeleteRequestRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    public UserDeleteRequest saveRequest(UserDeleteRequest request){
        return this.userDeleteRequestRepository.save(request);
    }

    public void acceptRequest(int id){
        UserDeleteRequest userDeleteRequest = this.userDeleteRequestRepository.findById(id);
        this.emailSenderService.acceptDeleteRequest(userDeleteRequest.getUser());
        this.userDeleteRequestRepository.delete(userDeleteRequest);
    }

    public void rejectRequest(int id){
        UserDeleteRequest userDeleteRequest = this.userDeleteRequestRepository.findById(id);
        this.emailSenderService.rejectDeleteRequest(userDeleteRequest.getUser());
        this.userDeleteRequestRepository.delete(userDeleteRequest);
    }

    public List<UserDeleteRequest> getAll(){
        return this.userDeleteRequestRepository.findAll();
    }

}
