package com.connected.userpost.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connected.userpost.Exception.PostNotFoundException;
import com.connected.userpost.Model.Comment;
import com.connected.userpost.Service.CommentService;


@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(
            @RequestBody Comment comment,
            @RequestParam String senderEmail,
            @RequestParam String receiverEmail,
            @RequestParam String postDate) {
        try {
            // Parse the date string to LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(postDate, formatter);

            Comment createdComment = commentService.createComment(comment, senderEmail, receiverEmail, localDate);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/comments/{senderEmail}/{receiverEmail}/{date}")
    public ResponseEntity<String> deleteComment(
            @PathVariable String senderEmail,
            @PathVariable String receiverEmail,
            @PathVariable String date) {
        try {
            // Parse the date string to LocalDate
            LocalDate localDate = LocalDate.parse(date);

            commentService.deleteComment(senderEmail, receiverEmail, localDate);
            return new ResponseEntity<>("Comments deleted successfully", HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/comments/{receiverEmail}/{date}")
    public ResponseEntity<List<Comment>> getAllCommentsForPost(
            @PathVariable String receiverEmail,
            @PathVariable String date) {
        try {
            // Parse the date string to LocalDate
            LocalDate localDate = LocalDate.parse(date);

            List<Comment> comments = commentService.getAllCommentsForPost(receiverEmail, localDate);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


//    @PostMapping("/comments/{email}/{date}")
//    public ResponseEntity<Comment> createComment(
//            @RequestBody Comment comment,
//            @PathVariable String email,
//            @PathVariable String date) {
//        try {
//            // Parse the date string to LocalDate
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate localDate = LocalDate.parse(date, formatter);
//
//            Comment createdComment = commentService.createComment(comment, email, localDate);
//            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
//        } catch (DateTimeParseException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
    
//    @DeleteMapping("/comments/{email}/{date}")
//    public ResponseEntity<String> deleteCommentByEmailAndDate(
//            @PathVariable String email,
//            @PathVariable String date) {
//        try {
//            // Parse the date string to LocalDate
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate localDate = LocalDate.parse(date, formatter);
//
//            boolean deleted = commentService.deleteCommentByEmailAndDate(email, localDate);
//
//            if (deleted) {
//                return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Comment not found or unauthorized", HttpStatus.NOT_FOUND);
//            }
//        } catch (DateTimeParseException e) {
//            return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
//        }
//    }
//    




    
//     //http://localhost:8080/comments?email=poo123@gmail.com & postId=2
//    @PostMapping("/comments")
//    public ResponseEntity<Comment> createComment(
//            @RequestBody Comment comment,
//            @RequestParam("postId") int postId,
//            @RequestParam("email") String email) {
//
//        Comment createdComment = commentService.createComment(comment, postId, email);
//        if (createdComment != null) {
//            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    
//  @GetMapping("/allcomments")
//  public ResponseEntity<List<Comment>> getAllComments() {
//    List<Comment> comments = commentService.getAllComments();
//    return new ResponseEntity<>(comments, HttpStatus.OK);
//  }
////http://localhost:8080/comments?email=user@example.com&postId=1
//  @GetMapping("/comments")
//  public ResponseEntity<List<Comment>> getCommentsByEmailAndPostId(
//          @RequestParam("email") String email,
//          @RequestParam("postId") int postId) {
//
//      List<Comment> comments = commentService.getCommentsByEmailAndPostId(email, postId);
//      if (!comments.isEmpty()) {
//          return new ResponseEntity<>(comments, HttpStatus.OK);
//      } else {
//          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//      }
//  }
//  
//
//  @PutMapping("/comments/{commentId}/{postId}/{email}")
//  public ResponseEntity<String> updateCommentByCommentIdAndPostIdAndEmail(
//          @PathVariable("commentId") int commentId,
//          @PathVariable("postId") int postId,
//          @PathVariable("email") String email,
//          @RequestBody Comment updatedComment) {
//
//      boolean updated = commentService.updateCommentByCommentIdAndPostIdAndEmail(commentId, postId, email, updatedComment);
//
//      if (updated) {
//          return new ResponseEntity<>("Comment updated successfully", HttpStatus.OK);
//      } else {
//          return new ResponseEntity<>("Comment not found or unauthorized", HttpStatus.NOT_FOUND);
//      }
//  }
//
//  @DeleteMapping("/comments/{commentId}/{postId}/{email}")
//  public ResponseEntity<String> deleteCommentByCommentIdAndPostIdAndEmail(
//          @PathVariable("commentId") int commentId,
//          @PathVariable("postId") int postId,
//          @PathVariable("email") String email) {
//
//      boolean deleted = commentService.deleteCommentByCommentIdAndPostIdAndEmail(commentId, postId, email);
//
//      if (deleted) {
//          return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
//      } else {
//          return new ResponseEntity<>("Comment not found or unauthorized", HttpStatus.NOT_FOUND);
//      }
//  }
//
// 




}
