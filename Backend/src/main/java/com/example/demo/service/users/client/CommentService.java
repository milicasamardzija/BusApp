package com.example.demo.service.users.client;

import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Comment;
import com.example.demo.repository.users.client.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAll(){
        return this.commentRepository.find();
    }

    public List<Comment> getAllForUsers(){
        return this.commentRepository.getAllForUsers();
    }

    public void add(String text, User user){
        Comment comment = new Comment(text, user, null);
        this.commentRepository.save(comment);
    }

    public void accept(int id){
        Comment comment = this.commentRepository.findById(id);
        comment.setAccepted(true);
        this.commentRepository.save(comment);
    }

    public void reject(int id){
        Comment comment = this.commentRepository.findById(id);
        comment.setAccepted(false);
        this.commentRepository.save(comment);
    }
}
