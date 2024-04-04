package com.connected.advertisement.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.connected.advertisement.exceptions.NotFoundException;
import com.connected.advertisement.model.AdvertisementPost;
import com.connected.advertisement.model.Comment;
import com.connected.advertisement.service.AdvertisementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/advertisements")
@Validated
public class AdvertisementController {
	
	@Autowired
    private AdvertisementService advertisementService;

    @GetMapping
    public List<AdvertisementPost> getAllPosts() {
        return advertisementService.getAllPosts();
    }

    @GetMapping("/advertiser/{email}")
    public List<AdvertisementPost> getAllPostsByEmail(@PathVariable String email) {
        return advertisementService.getAllPostsByEmail(email);
    }

    @GetMapping("/advertiser/{email}/{postDate}")
    public ResponseEntity<AdvertisementPost> getPostByEmailAndDate(
            @PathVariable String email,
            @PathVariable String postDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);
            Optional<AdvertisementPost> post = advertisementService.getPostByEmailAndDate(email, date);
            return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (DateTimeParseException ex) {
            throw new NotFoundException("Invalid email or post date format");
        }
    }

    @DeleteMapping("/advertiser/{email}/{postDate}")
    public ResponseEntity<?> deletePostByEmailAndDate(@PathVariable String email, @PathVariable String postDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);
            advertisementService.deletePostByEmailAndDate(email, date);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException ex) {
            throw new NotFoundException("Invalid email or post date format");
        }
    }

    @DeleteMapping("/advertiser/{email}")
    public ResponseEntity<?> deleteAllPostsByEmail(@PathVariable String email) {
        advertisementService.deleteAllPostsByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/advertiser/posts/{postId}")
    public ResponseEntity<?> deleteAdvertisementPost(
            @PathVariable Long postId,
            @RequestParam String email) {
        try {
            advertisementService.deletePostByIdAndEmail(postId, email);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/advertiser/{email}/{postDate}")
    public ResponseEntity<?> updatePost(
            @PathVariable String email,
            @PathVariable String postDate,
            @Valid @RequestBody AdvertisementPost updatedPost) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);
            advertisementService.updatePost(email, date, updatedPost);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException ex) {
            throw new NotFoundException("Invalid post date format");
        }
    }

    @PostMapping("/like")
    public ResponseEntity<?> likePost(
            @RequestParam String email,
            @RequestParam String postDate) {
        try {
            // Parse the postDate string with the expected format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);

            // Call the service method to like the post
            advertisementService.likePostByEmailAndDate(email, date);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            return ResponseEntity.badRequest().body("Invalid date format");
        }
    }
    @PostMapping("/share")
    public ResponseEntity<?> sharePostByEmailAndDate(@RequestParam String email, @RequestParam String postDate) {
        try {
            // Parse the postDate string with the expected format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);

            advertisementService.sharePostByEmailAndDate(email, date);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException ex) {
            return ResponseEntity.badRequest().body("Invalid date format");
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestParam Long postId, @RequestBody Comment comment) {
        advertisementService.addComment(postId, comment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/advertiser/posts/{postId}/comments")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long postId,
            @RequestParam String commentText,
            @RequestParam String commenterName) {
        try {
            advertisementService.deleteComment(postId, commentText, commenterName);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/advertiser")
    public ResponseEntity<?> createAdvertisementPost(
            @RequestParam String email,
            @RequestParam MultipartFile image) {
        try {
            long imageSize = image.getSize();
            // Check image size and perform other validations
            byte[] imageBytes = image.getBytes();
            AdvertisementPost createdPost = advertisementService.createAdvertisementPost(email, imageBytes);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
