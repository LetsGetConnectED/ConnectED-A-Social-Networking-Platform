package com.example.demo.Controller;

import java.util.List;

import java.util.stream.Collectors;

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
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.PostService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.CommentDTO;
import com.example.demo.mapper.CommentMapper;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService service;
    @GetMapping("/example")
    public ResponseEntity<List<CommentDTO>> getComments() {
        List<Comment> comments = commentService.getAllComments();
        List<CommentDTO> commentDTOs = comments.stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }
    
    //http://localhost:8080/comments?userId=1&postId=2
    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody CommentDTO commentDTO,
            @RequestParam("userId") int userId,
            @RequestParam("postId") int postId) {

        Comment comment = CommentMapper.toEntity(commentDTO);
        Comment createdComment = commentService.createComment(comment, userId, postId);
        
        if (createdComment != null) {
            CommentDTO createdCommentDTO = CommentMapper.toDTO(createdComment);
            return new ResponseEntity<>(createdCommentDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    @GetMapping("comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") int id) {
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            CommentDTO commentDTO = CommentMapper.toDTO(comment);
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
   
   
     //http://localhost:8080/comments/5?userId=2&postId=2
    @PutMapping("comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("id") int id,
                                                     @RequestBody CommentDTO updatedCommentDTO,
                                                     @RequestParam("userId") int userId,
                                                     @RequestParam("postId") int postId) throws Exception {
        Comment updatedComment = CommentMapper.toEntity(updatedCommentDTO);
        // Retrieve the User object based on the userId
        User user = userService.findUserById(userId);
        // Retrieve the Post object based on the postId
        Post post = service.getPostById(postId).orElse(null);
        if (user == null || post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Associate the User and Post objects with the Comment entity
        updatedComment.setUser(user);
        updatedComment.setPost(post);
        
        Comment comment = commentService.updateComment(id, updatedComment, postId);
        
        if (comment != null) {
            CommentDTO commentDTO = CommentMapper.toDTO(comment);
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }


    
    @DeleteMapping("comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int id) {
        boolean deleted = commentService.deleteComment(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
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
