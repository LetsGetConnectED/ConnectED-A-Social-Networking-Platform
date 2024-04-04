package com.connected.advertisement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connected.advertisement.exceptions.NotFoundException;
import com.connected.advertisement.model.AdvertisementPost;
import com.connected.advertisement.model.Advertiser;
import com.connected.advertisement.model.Comment;
import com.connected.advertisement.repo.AdvertisementPostRepository;
import com.connected.advertisement.repo.AdvertiserRepository;

import jakarta.validation.Valid;

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementPostRepository postRepository;

    @Autowired
    private AdvertiserRepository advertiserRepository;

    @Override
    public List<AdvertisementPost> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<AdvertisementPost> getAllPostsByEmail(String email) {
        return postRepository.findAllByEmail(email);
    }
    @Override
    public Optional<AdvertisementPost> getPostByEmailAndDate(String email, LocalDate postDate) {
        return postRepository.findByEmailAndPostDate(email, postDate.atStartOfDay());
    }


//    @Override
//    public Optional<AdvertisementPost> getPostByEmailAndDate(String email, LocalDateTime postDate) {
//        return postRepository.findByEmailAndPostDate(email, postDate);
//    }

    @Override
    public void deletePostByEmailAndDate(String email, LocalDateTime postDate) {
        postRepository.deleteByEmailAndPostDate(email, postDate);
    }

    @Override
    public void deleteAllPostsByEmail(String email) {
        postRepository.deleteAllByEmail(email);
    }

    @Override
    public void updatePost(String email, LocalDateTime postDate, AdvertisementPost updatedPost) {
        Optional<AdvertisementPost> existingPost = postRepository.findByEmailAndPostDate(email, postDate);
        if (existingPost.isPresent()) {
            AdvertisementPost post = existingPost.get();
            // Update the necessary fields
			/* post.setSomeField(updatedPost.getSomeField()); */
            // Update other fields as needed
            postRepository.save(post);
        }
    }

    @Override
    public void likePost(Long postId) {
        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            AdvertisementPost post = optionalPost.get();
            post.setLikes(post.getLikes() + 1);
            postRepository.save(post);
        } else {
            throw new NotFoundException("Post not found with id: " + postId);
        }
    }

    @Override
    public void addComment(Long postId, Comment comment) {
        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            AdvertisementPost post = optionalPost.get();
            post.getComments().add(comment);
            postRepository.save(post);
        } else {
            throw new NotFoundException("Post not found with id: " + postId);
        }
    }

//    @Override
//    public void createAdvertisementPostForAdvertiser(String email, AdvertisementPost advertisementPost) {
//        // Find the advertiser by email
//        Advertiser advertiser = advertiserRepository.findByEmail(email)
//            .orElseThrow(() -> new NotFoundException("Advertiser not found with email: " + email));
//
//        // Here you can add logic to set the post date and perform any other necessary operations
//        advertisementPost.setPostDate(LocalDateTime.now());
//
//        // Save the advertisement post
//        postRepository.save(advertisementPost);
//    }
    
    @Override
    public AdvertisementPost createAdvertisementPost(String email, byte[] image) {
        AdvertisementPost post = new AdvertisementPost();
        post.setEmail(email);
        post.setPostDate(LocalDateTime.now());
        post.setImage(image);
        return postRepository.save(post);
    }

    // Implement other functionalities like nested comments and share
}
