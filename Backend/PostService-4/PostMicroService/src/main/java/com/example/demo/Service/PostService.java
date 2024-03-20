
package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.Model.Post;


public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(int id);
    Post createPost(Post post);
    Post updatePost(int id, Post updatedPost);
    void deletePost(int id);
   Post createNewPost(Post post,Integer userId)throws Exception;
   
   
    List<Post>findPostByUserId(Integer userId);
    //Post createNewPost(Post post, Integer userId, MultipartFile image) throws Exception;


	
	//Post updatePost(Integer postId, Post updatedPost, Integer userId) throws Exception;
   
   
    
}
