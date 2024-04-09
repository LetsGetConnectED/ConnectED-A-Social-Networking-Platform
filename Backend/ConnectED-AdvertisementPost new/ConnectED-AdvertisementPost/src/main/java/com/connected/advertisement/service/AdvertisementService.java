package com.connected.advertisement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.connected.advertisement.model.AdvertisementPost;
import com.connected.advertisement.model.Advertiser;
import com.connected.advertisement.model.Comment;
import com.connected.advertisement.model.User;

public interface AdvertisementService {

    List<AdvertisementPost> getAllPosts();
    List<AdvertisementPost> getAllPostsByAdvertiserEmail(String advertiserEmail);
    Optional<AdvertisementPost> getPostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate);
    void deletePostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate);
    void deleteAllPostsByAdvertiserEmail(String advertiserEmail);
    void updatePost(String advertiserEmail, LocalDate postDate, AdvertisementPost updatedPost);
    AdvertisementPost createAdvertisementPost(String advertiserEmail, String caption, String link, byte[] image);
    void deletePostByIdAndAdvertiserEmail(Long postId, String advertiserEmail);
	void sharePostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate);
	//void likePostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate);
	//AdvertisementPost addCommentToPost(String senderEmail, String receiverEmail, LocalDate postDate,
			//String commentText);
	//void likePostByUserAndDate(User user, Advertiser advertiser, LocalDate postDate);
	void likePostByUserAndDate(User user, Advertiser advertiser, LocalDate postDate, Long postId);
	 List<String> getLikedUsersByPostId(Long postId);
	AdvertisementPost addCommentToPost(String receiverEmail, String senderEmail, String commenterEmail, 
			LocalDate postDate, String commentText);
	
	
	void deleteComment(String receiverEmail, String senderEmail, LocalDate postDate, String comment);
	 List<Comment> getAllCommentsByPostId(Long postId);
}
