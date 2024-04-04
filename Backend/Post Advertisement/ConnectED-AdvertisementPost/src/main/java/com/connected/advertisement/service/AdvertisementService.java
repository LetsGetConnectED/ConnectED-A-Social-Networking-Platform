package com.connected.advertisement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.connected.advertisement.model.AdvertisementPost;
import com.connected.advertisement.model.Comment;

public interface AdvertisementService {
    List<AdvertisementPost> getAllPosts();
    List<AdvertisementPost> getAllPostsByEmail(String email);
    //Optional<AdvertisementPost> getPostByEmailAndDate(String email, LocalDateTime postDate);
    Optional<AdvertisementPost> getPostByEmailAndDate(String email, LocalDate postDate);
    //Optional<AdvertisementPost> getPostByEmailAndDate(String email, String postDateStr);
    void deletePostByEmailAndDate(String email, LocalDateTime postDate);
    void deleteAllPostsByEmail(String email);
    void updatePost(String email, LocalDateTime postDate, AdvertisementPost updatedPost);
    void likePost(Long postId);
    void addComment(Long postId, Comment comment);
    // Other methods for nested comments and share
   // void createAdvertisementPostForAdvertiser(String email, AdvertisementPost advertisementPost);
    AdvertisementPost createAdvertisementPost(String email, byte[] image);
}
