package com.example.demo.controller.users.client;

import com.example.demo.dto.users.client.CommentRequest;
import com.example.demo.dto.users.client.CommentResponse;
import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Comment;
import com.example.demo.service.users.client.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAll(){
        List<CommentResponse> ret = new ArrayList<>();

        for (Comment comment: this.commentService.getAll()
             ) {
            ret.add(new CommentResponse(comment.getId(), comment.getText(), comment.getAccepted(), comment.getUser().getName(), comment.getUser().getSurname(), comment.getUser().getPicture()));
        }
        
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(value = "/forUsers")
    public ResponseEntity<List<CommentResponse>> getAllForUsers(){
        List<CommentResponse> ret = new ArrayList<>();

        for (Comment comment: this.commentService.getAllForUsers()
        ) {
            ret.add(new CommentResponse(comment.getId(), comment.getText(), comment.getAccepted(), comment.getUser().getName(), comment.getUser().getSurname(), comment.getUser().getPicture()));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<HttpStatus> accept(@PathVariable int id){
        this.commentService.accept(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/reject/{id}")
    public ResponseEntity<HttpStatus> reject(@PathVariable int id){
        this.commentService.reject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody CommentRequest commentRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        this.commentService.add(commentRequest.text, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
