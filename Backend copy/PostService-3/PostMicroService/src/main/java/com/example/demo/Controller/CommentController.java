package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Comment;
import com.example.demo.Service.CommentService;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    //http://localhost:8080/comments?userId=1&postId=2
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(
            @RequestBody Comment comment,
            @RequestParam("userId") int userId,
            @RequestParam("postId") int postId) {

        Comment createdComment = commentService.createComment(comment, userId, postId);
        if (createdComment != null) {
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") int id) {
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    @PutMapping("comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") int id, @RequestBody Comment updatedComment) {
        Comment comment = commentService.updateComment(id, updatedComment);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
    
    @DeleteMapping("comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") int id) {
        boolean deleted = commentService.deleteComment(id);
        if (deleted) {
            return new ResponseEntity<>("Comment with ID " + id + " has been successfully deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Comment with ID " + id + " not found", HttpStatus.NOT_FOUND); 
        }
    }

//    @DeleteMapping("comments/{id}")
//    public ResponseEntity<Void> deleteComment(@PathVariable("id") int id) {
//        boolean deleted = commentService.deleteComment(id);
//        if (deleted) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
//        }
//    }
}
