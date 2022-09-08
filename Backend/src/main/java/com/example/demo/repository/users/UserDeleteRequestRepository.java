package com.example.demo.repository.users;

import com.example.demo.model.users.UserDeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeleteRequestRepository extends JpaRepository<UserDeleteRequest, Integer> {
    UserDeleteRequest findById(int id);
}
