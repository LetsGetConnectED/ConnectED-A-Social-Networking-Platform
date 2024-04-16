package com.connected.post.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.connected.post.model.AdvertisementPost;
import com.connected.post.model.Advertiser;
import com.connected.post.model.AdvertiserComment;
import com.connected.post.model.User;

public interface AdvertisementService {

	List<AdvertisementPost> getAllPosts();

	List<AdvertisementPost> getAllPostsByAdvertiserEmail(String advertiserEmail);

	Optional<AdvertisementPost> getPostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate);

//	void deletePostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate);

	void deleteAllPostsByAdvertiserEmail(String advertiserEmail);

	void updatePost(String advertiserEmail, LocalDate postDate, AdvertisementPost updatedPost);

	AdvertisementPost createAdvertisementPost(String advertiserEmail, String caption, String link, byte[] image);

	void deletePostByIdAndAdvertiserEmail(Long postId, String advertiserEmail);

	
	void likePostByUserAndDate(User user, Advertiser advertiser, LocalDate postDate, Long postId);

	List<String> getLikedUsersByPostId(Long postId);

	AdvertisementPost addCommentToPost(String receiverEmail, String senderEmail, String commenterEmail,
			LocalDate postDate, String commentText, Long parentCommentId);

	

	List<AdvertiserComment> getAllCommentsByPostId(Long postId);

	List<AdvertiserComment> getRepliesToComment(Long postId, Long commentId);

	
	List<Long> getLikedPostsByUserEmail(String userEmail);

	
	void addParentComment(String receiverEmail, String senderEmail, Long postId, LocalDate postDate,
			String commentText);

	

	void sharePost(String advertiserEmail, LocalDate localDate);

	void deleteComment(String receiverEmail, String senderEmail, LocalDate postDate, String commenterEmail,
			String comment);

	void deleteAdvertisementPost(String advertiserEmail, String postDate);

	

	void deleteAllComments(Long postId);

	

	

}
