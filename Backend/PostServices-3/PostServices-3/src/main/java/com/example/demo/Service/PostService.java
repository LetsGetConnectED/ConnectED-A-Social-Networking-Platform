
package com.example.demo.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



import com.example.demo.Model.Post;


public interface PostService {
 List<Post> getAllPosts();
 List<Post> getPostsByUserEmail(String email);
 Post createNewPost(Post post, String email) throws Exception;
  Optional<Post> getPostById(int id);
  Post updatePost(int id, Post updatedPost, String email) throws Exception;
  void deletePost(int id, String email) throws Exception;

  
  byte[] getImageBytes(String email, int postId);
  void updateImageBytes(String email, int postId, byte[] imageBytes);

}
