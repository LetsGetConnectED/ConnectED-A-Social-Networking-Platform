
package com.connected.userpost.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.connected.userpost.Exception.PostNotFoundException;
import com.connected.userpost.Model.Post;


public interface PostService {
	
	 List<Post> getAllPosts();
	    Optional<Post> getPostById(int id);
	    List<Post> getPostsByUserEmail(String email);
	    Optional<List<Post>> getPostsByEmailAndDate(String email, LocalDate date);
	    Post createNewPost(Post post, String email, LocalDate date);
	    void likePostByEmailAndDate(String email, LocalDate date);
	    void sharePostByEmailAndDate(String email, LocalDate date);
	    void deletePostsByEmail(String email);
	    void deletePostsByEmailAndDate(String email, LocalDate date);
// List<Post> getAllPosts();
// List<Post> getPostsByUserEmail(String email);
// 
//  Optional<Post> getPostById(int id);
//  Post updatePost(int id, Post updatedPost, String email) throws Exception;
//  void deletePostsByEmail(String email) throws PostNotFoundException;
//  void deletePostsByEmailAndDate(String email, Date date) throws PostNotFoundException;
//  void deletePostsByEmailAndDate(String email, LocalDateTime date);
//  
//  
//  
//  
//  
//  Post updatePostByEmail(Post updatedPost, String email) throws Exception;
//
//  
//  
//  Post updatePost(Post updatedPost, String email) throws Exception;
//
//   
//
//  
// 
//  void updateImageBytes(String email, int postId, byte[] imageBytes);
//
// 
//  Post savePost(Post post);
//
//  
//  Post createNewPost(Post post, String email, LocalDateTime currentDateTime) throws Exception;
//
//  //Optional<List<Post>> getPostsByEmailAndDate(String email, LocalDateTime date);
//  Optional<List<Post>> getPostsByEmailAndDate(String email, LocalDate date);
//
//  void likePostByEmailAndDate(String email, LocalDateTime date);
//  void sharePostByEmailAndDate(String email, LocalDateTime date);
//
//void deletePostsByDate(LocalDateTime dateTime);
//void updatePostsByEmailAndDate(String email, LocalDateTime dateTime, Post updatedPost);
//
//
//
//
//
		

	

	
	
	
    
}
