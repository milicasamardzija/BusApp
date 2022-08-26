package com.example.demo.repository.users.client;

import com.example.demo.model.users.client.PassengerRegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRegistrationTokenRepository extends JpaRepository<PassengerRegistrationToken, Integer> {

    PassengerRegistrationToken findByCode(String code);
}
