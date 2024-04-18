package com.connected.post.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.connected.post.model.UserPost;
import com.connected.post.model.UserComment;


public interface PostService {
	
	 List<UserPost> getAllPosts();
	    Optional<UserPost> getPostById(int id);
	    List<UserPost> getPostsByUserEmail(String email);
	    Optional<List<UserPost>> getPostsByEmailAndDate(String email, LocalDate date);
	    UserPost createNewPost(UserPost post, String email, LocalDate date);
	    
	    void sharePostByEmailAndDate(String email, LocalDate date);
	    void deletePostsByEmail(String email);
	    void deletePostsByEmailAndDate(String email, LocalDate date);
		void likeOrUnlikePost(String user_email, Long postId, LocalDate date, String postOwnerEmail);
		

		List<String> getUsersWhoLikedPost(Long postId);
		
		
		List<UserComment> getAllCommentsByPostId(Long postId);
		List<UserComment> getRepliesToComment(Long postId, Long commentId);
		
		
		 void addParentComment(String receiverEmail, String senderEmail, LocalDate postDate, Long postId, String commentText);
		void addCommentToPost(String receiverEmail, String senderEmail, String commenterEmail, LocalDate postDate,
				String commentText, Long parentCommentId, Long postId);
		void deleteComment(String receiverEmail, String senderEmail, LocalDate postDate, String commentText,
				String commenterEmail);
		List<Long> getLikedPostIds(String userEmail);
}
		