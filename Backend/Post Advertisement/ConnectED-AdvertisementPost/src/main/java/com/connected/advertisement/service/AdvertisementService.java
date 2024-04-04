package com.connected.advertisement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.connected.advertisement.exceptions.NotFoundException;
import com.connected.advertisement.model.AdvertisementPost;
import com.connected.advertisement.model.Comment;

public interface AdvertisementService {
	
	 List<AdvertisementPost> getAllPosts();
	    List<AdvertisementPost> getAllPostsByEmail(String email);
	    Optional<AdvertisementPost> getPostByEmailAndDate(String email, LocalDate postDate);
	    void deletePostByEmailAndDate(String email, LocalDate postDate);
	    void deleteAllPostsByEmail(String email);
	    void updatePost(String email, LocalDate postDate, AdvertisementPost updatedPost);
	   
	    void addComment(Long postId, Comment comment);
	    AdvertisementPost createAdvertisementPost(String email, byte[] image);
	    void deletePostByIdAndEmail(Long postId, String email);
	    void deleteComment(Long postId, String commentText, String commenterName) throws NotFoundException;
		void likePostByEmailAndDate(String email, LocalDate postDate);
		void sharePostByEmailAndDate(String email, LocalDate postDate);
	}



