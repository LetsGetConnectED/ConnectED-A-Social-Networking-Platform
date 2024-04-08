package com.connected.advertisement.controller;

import java.time.LocalDate;
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
import com.connected.advertisement.model.Advertiser;
import com.connected.advertisement.model.Comment;
import com.connected.advertisement.model.User;
import com.connected.advertisement.repo.AdvertiserRepository;
import com.connected.advertisement.repo.UserRepository;
import com.connected.advertisement.service.AdvertisementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/advertisements")
@Validated
public class AdvertisementController {
    
    @Autowired
    private AdvertisementService advertisementService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AdvertiserRepository advertiserRepository;
    
   
    @GetMapping
    public List<AdvertisementPost> getAllPosts() {
        return advertisementService.getAllPosts();
    }

    @GetMapping("/advertiser/{advertiserEmail}")
    public List<AdvertisementPost> getAllPostsByAdvertiserEmail(@PathVariable String advertiserEmail) {
        return advertisementService.getAllPostsByAdvertiserEmail(advertiserEmail);
    }

    @GetMapping("/advertiser/{advertiserEmail}/{postDate}")
    public ResponseEntity<AdvertisementPost> getPostByAdvertiserEmailAndDate(
            @PathVariable String advertiserEmail,
            @PathVariable String postDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);
            Optional<AdvertisementPost> post = advertisementService.getPostByAdvertiserEmailAndDate(advertiserEmail, date);
            return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (DateTimeParseException ex) {
            throw new NotFoundException("Invalid email or post date format");
        }
    }

    @DeleteMapping("/advertiser/{advertiserEmail}/{postDate}")
    public ResponseEntity<?> deletePostByAdvertiserEmailAndDate(@PathVariable String advertiserEmail, @PathVariable String postDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);
            advertisementService.deletePostByAdvertiserEmailAndDate(advertiserEmail, date);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException ex) {
            throw new NotFoundException("Invalid email or post date format");
        }
    }

    @DeleteMapping("/advertiser/{advertiserEmail}")
    public ResponseEntity<?> deleteAllPostsByAdvertiserEmail(@PathVariable String advertiserEmail) {
        advertisementService.deleteAllPostsByAdvertiserEmail(advertiserEmail);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/advertiser/{advertiserEmail}/{postDate}")
    public ResponseEntity<?> updatePost(
            @PathVariable String advertiserEmail,
            @PathVariable String postDate,
            @Valid @RequestBody AdvertisementPost updatedPost) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);
            advertisementService.updatePost(advertiserEmail, date, updatedPost);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException ex) {
            throw new NotFoundException("Invalid post date format");
        }
    }

    @PostMapping("/advertiser")
    public ResponseEntity<?> createAdvertisementPost(
            @RequestParam String advertiserEmail,
            @RequestParam String caption,
            @RequestParam String link,
            @RequestParam MultipartFile image) {
        try {
            long imageSize = image.getSize();
            // Check image size and perform other validations
            byte[] imageBytes = image.getBytes();
            AdvertisementPost createdPost = advertisementService.createAdvertisementPost(advertiserEmail, caption, link, imageBytes);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PostMapping("/like")
//    public ResponseEntity<?> likePost(
//            @RequestParam String userEmail,
//            @RequestParam String advertiserEmail,
//            @RequestParam String postDate) {
//        try {
//            // Parse the postDate string with the expected format
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate date = LocalDate.parse(postDate, formatter);
//
//            // Find the user by email
//            User user = userRepository.findByEmail(userEmail)
//                    .orElseThrow(() -> new NotFoundException("User not found"));
//
//            // Find the advertiser by email
//            Advertiser advertiser = advertiserRepository.findByEmail(advertiserEmail)
//                    .orElseThrow(() -> new NotFoundException("Advertiser not found"));
//
//            // Call the service method to like the post
//            advertisementService.likePostByUserAndDate(user, advertiser, date);
//            return ResponseEntity.noContent().build();
//        } catch (DateTimeParseException e) {
//            // Handle invalid date format
//            return ResponseEntity.badRequest().body("Invalid date format");
//        }
//    }
    
    @PostMapping("/like")
    public ResponseEntity<?> likePost(
            @RequestParam String userEmail,
            @RequestParam String advertiserEmail,
            @RequestParam String postDate,
            @RequestParam Long postId) {
        try {
           
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(postDate, formatter);

           
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new NotFoundException("User not found"));

            
            Advertiser advertiser = advertiserRepository.findByEmail(advertiserEmail)
                    .orElseThrow(() -> new NotFoundException("Advertiser not found"));

           
            advertisementService.likePostByUserAndDate(user, advertiser, date, postId);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException e) {
            
            return ResponseEntity.badRequest().body("Invalid date format");
        }
    }
    
 
    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestParam String receiverEmail, 
                                        @RequestParam String senderEmail,
                                        @RequestParam String commenterEmail,
                                       
                                        @RequestParam String postDate,
                                        @RequestBody Comment comment) {
        LocalDate parsedPostDate;
        try {
            parsedPostDate = LocalDate.parse(postDate);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid post date format");
        }
        
        advertisementService.addCommentToPost(receiverEmail, senderEmail, commenterEmail,  parsedPostDate, comment.getComment());
        return ResponseEntity.noContent().build();
    }



   


}
