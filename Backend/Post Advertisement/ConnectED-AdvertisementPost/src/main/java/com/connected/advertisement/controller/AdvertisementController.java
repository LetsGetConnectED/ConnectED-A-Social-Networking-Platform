package com.connected.advertisement.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping     //Vaibhav
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
            // Parse the postDate string with the expected format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            LocalDate date = LocalDate.parse(postDate, formatter);

            // Call the service method with the parsed LocalDate
            Optional<AdvertisementPost> post = advertisementService.getPostByEmailAndDate(email, date);
            
            // Return ResponseEntity based on the Optional result
            return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception ex) {
            // Handle any parsing errors or other exceptions
            throw new NotFoundException("Invalid email or post date format");
        }
    }
//    @GetMapping("/advertiser/{email}/{postDate}")
//    public ResponseEntity<AdvertisementPost> getPostByEmailAndDate(
//            @PathVariable String email,
//            @PathVariable String postDate) {
//        try {
//            LocalDate date = LocalDate.parse(postDate);
//            Optional<AdvertisementPost> post = advertisementService.getPostByEmailAndDate(email, date);
//            return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        } catch (Exception ex) {
//            throw new NotFoundException("Invalid email or post date format");
//        }
//    }


//    @GetMapping("/advertiser/{email}/{postDate}")
//    public ResponseEntity<AdvertisementPost> getPostByEmailAndDate(@PathVariable String email, @PathVariable LocalDateTime postDate) {
//        Optional<AdvertisementPost> post = advertisementService.getPostByEmailAndDate(email, postDate);
//        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/advertiser/{email}/{postDate}")  //Sowndarya
    public ResponseEntity<?> deletePostByEmailAndDate(@PathVariable String email, @PathVariable LocalDateTime postDate) {
        advertisementService.deletePostByEmailAndDate(email, postDate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/advertiser/{email}")
    public ResponseEntity<?> deleteAllPostsByEmail(@PathVariable String email) {
        advertisementService.deleteAllPostsByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/advertiser/{email}/{postDate}")   //Vaibhav
    public ResponseEntity<?> updatePost(@PathVariable String email, @PathVariable LocalDateTime postDate, @Valid @RequestBody AdvertisementPost updatedPost) {
        advertisementService.updatePost(email, postDate, updatedPost);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/like")//Poojitha
    public ResponseEntity<?> likePost(@RequestParam Long postId) {
        advertisementService.likePost(postId);
        return ResponseEntity.noContent().build();
    }


    
    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestParam Long postId, @RequestBody Comment comment) {
        advertisementService.addComment(postId, comment);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/advertiser/{email}")
//    public ResponseEntity<?> postAdvertisementByAdvertiserEmail(@PathVariable String email,  @RequestBody AdvertisementPost advertisementPost) {
//        advertisementService.createAdvertisementPostForAdvertiser(email, advertisementPost);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
    
    private static final long MAX_IMAGE_SIZE_5MB = 5 * 1024 * 1024; // 5MB in bytes
    private static final long MAX_IMAGE_SIZE_10MB = 10 * 1024 * 1024; // 10MB in bytes

    @PostMapping("/advertiser")
    public ResponseEntity<?> createAdvertisementPost(
            @RequestParam String email,
            @RequestParam MultipartFile image) {
        try {
            // Check image size
            long imageSize = image.getSize();
            if (imageSize > MAX_IMAGE_SIZE_10MB) {
                return ResponseEntity.badRequest().body("Image size exceeds the allowed limit of 10MB");
            }
            // If you want to allow only up to 5MB:
            // if (imageSize > MAX_IMAGE_SIZE_5MB) {
            //     return ResponseEntity.badRequest().body("Image size exceeds the allowed limit of 5MB");
            // }

            byte[] imageBytes = image.getBytes();
            AdvertisementPost createdPost = advertisementService.createAdvertisementPost(email, imageBytes);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
