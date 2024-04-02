
package com.example.demo.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import com.example.demo.Model.Post;


public interface PostService {
 List<Post> getAllPosts();
 List<Post> getPostsByUserEmail(String email);

  Optional<Post> getPostById(int id);
  Post updatePost(int id, Post updatedPost, String email) throws Exception;
  void deletePost(int id, String email) throws Exception;

   
//    byte[] getImageBytes(int postId);
//    void updateImageBytes(int postId, byte[] imageBytes);
//	void updateImageBytes1(int postId, byte[] imageBytes);
  
  byte[] getImageBytes(String email, int postId);
  void updateImageBytes(String email, int postId, byte[] imageBytes);
Post createNewPost(Post post, String email) throws Exception;

Post createNewPost(Post post, String email, Date date) throws Exception;
	

	
	
	
	//Post createNewPost(Post post, String email, byte[] imageBytes) throws IOException, Exception;
    
}
