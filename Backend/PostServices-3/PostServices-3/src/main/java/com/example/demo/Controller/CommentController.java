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
    
    // http://localhost:8080/comments?email=poo123@gmail.com & postId=2
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(
            @RequestBody Comment comment,
            @RequestParam("postId") int postId,
            @RequestParam("email") String email) {

        Comment createdComment = commentService.createComment(comment, postId, email);
        if (createdComment != null) {
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  @GetMapping("/allcomments")
  public ResponseEntity<List<Comment>> getAllComments() {
    List<Comment> comments = commentService.getAllComments();
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }
//http://localhost:8080/comments?email=user@example.com&postId=1
  @GetMapping("/comments")
  public ResponseEntity<List<Comment>> getCommentsByEmailAndPostId(
          @RequestParam("email") String email,
          @RequestParam("postId") int postId) {

      List<Comment> comments = commentService.getCommentsByEmailAndPostId(email, postId);
      if (!comments.isEmpty()) {
          return new ResponseEntity<>(comments, HttpStatus.OK);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
  

  @PutMapping("/comments/{commentId}/{postId}/{email}")
  public ResponseEntity<String> updateCommentByCommentIdAndPostIdAndEmail(
          @PathVariable("commentId") int commentId,
          @PathVariable("postId") int postId,
          @PathVariable("email") String email,
          @RequestBody Comment updatedComment) {

      boolean updated = commentService.updateCommentByCommentIdAndPostIdAndEmail(commentId, postId, email, updatedComment);

      if (updated) {
          return new ResponseEntity<>("Comment updated successfully", HttpStatus.OK);
      } else {
          return new ResponseEntity<>("Comment not found or unauthorized", HttpStatus.NOT_FOUND);
      }
  }

  @DeleteMapping("/comments/{commentId}/{postId}/{email}")
  public ResponseEntity<String> deleteCommentByCommentIdAndPostIdAndEmail(
          @PathVariable("commentId") int commentId,
          @PathVariable("postId") int postId,
          @PathVariable("email") String email) {

      boolean deleted = commentService.deleteCommentByCommentIdAndPostIdAndEmail(commentId, postId, email);

      if (deleted) {
          return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
      } else {
          return new ResponseEntity<>("Comment not found or unauthorized", HttpStatus.NOT_FOUND);
      }
  }

 




}
