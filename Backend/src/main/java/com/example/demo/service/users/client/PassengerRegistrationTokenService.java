package com.example.demo.service.users.client;

import com.example.demo.repository.users.client.PassengerRegistrationTokenRepository;
import com.example.demo.model.users.client.PassengerRegistrationToken;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerRegistrationTokenService {

    @Autowired
    PassengerRegistrationTokenRepository passengerRegistrationTokenRepository;

    public String save(String email) {
        String code = generateCode();
        passengerRegistrationTokenRepository.save(new PassengerRegistrationToken(email, code));

        return code;
    }

    public String generateCode() {
        return RandomStringUtils.randomAlphabetic(20);
    }

    public String findByCode(String code) {
        PassengerRegistrationToken token = this.passengerRegistrationTokenRepository.findByCode(code);
        return token.getEmail();
    }
}
