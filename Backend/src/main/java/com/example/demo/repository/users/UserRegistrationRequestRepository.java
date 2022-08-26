package com.example.demo.repository.users;

import com.example.demo.model.users.UserRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRequestRepository extends JpaRepository<UserRegistrationRequest, Integer> {

    UserRegistrationRequest findByEmail(String email);

    UserRegistrationRequest findById(int id);
}
