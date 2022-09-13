package com.example.demo.repository.users.client;

import com.example.demo.model.users.client.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c where c.accepted = true")
    List<Comment> getAllForUsers();
    Comment findById(int id);
    @Query("select c from Comment c where c.accepted = null")
    List<Comment> find();
}
