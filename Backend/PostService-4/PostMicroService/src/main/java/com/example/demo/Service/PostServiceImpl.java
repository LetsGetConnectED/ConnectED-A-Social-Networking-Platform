
package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;


import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.UserRepository;



@Service

public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository repo;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Post> getAllPosts() {
	    return repo.findAll();
	}
	
	@Override
	public Optional<Post> getPostById(int id) {
	    return repo.findById(id);
	}
	@Override
	public Post createPost(Post post) {
	    return repo.save(post);
	}
	
	
	
	
	
	@Override
	public void deletePost(int id) {
	    repo.deleteById(id);
	}

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		
		 Post newPost = new Post();
		 newPost.setContent(post.getContent());
		 newPost.setShares(post.getShares());
		 newPost.setLikes(post.getLikes());
		 newPost.setComments(post.getComments());
		 newPost.setImageUrl(post.getImageUrl());
		 newPost.setVideo(post.getVideo());
		 
		 newPost.setUser(user);
		 
		 
		 return repo.save(newPost);
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		
		return repo.findPostByUserId(userId);
	}

//	@Override
//	public Post updatePost(int id, Post updatedPost) {
//		 updatedPost.setId(id);
//		    return repo.save(updatedPost);
//	}
	@Override
	public Post updatePost(int id, Post updatedPost) {
	    // Retrieve the existing post by ID
	    Optional<Post> optionalPost = repo.findById(id);
	    if (!optionalPost.isPresent()) {
	        // Handle case where post with given ID doesn't exist
	        // You may throw an exception or return null based on your requirement
	        // Here, I'm throwing an IllegalArgumentException
	        throw new IllegalArgumentException("Post with ID " + id + " not found");
	    }

	    // Get the existing post
	    Post existingPost = optionalPost.get();

	    // Update the fields of the existing post with the corresponding values from updatedPost
	    existingPost.setContent(updatedPost.getContent());
	    existingPost.setShares(updatedPost.getShares());
	    existingPost.setLikes(updatedPost.getLikes());
	    existingPost.setImageUrl(updatedPost.getImageUrl());
	    existingPost.setVideo(updatedPost.getVideo());
	    
	    // Ensure that user information is not null before setting it in the existing post
	    if (updatedPost.getUser() != null) {
	        existingPost.setUser(updatedPost.getUser());
	    }

	    // Save and return the updated post
	    return repo.save(existingPost);
	}


	
	

//	@Override
//	public Post updatePost(Integer postId, Post updatedPost, Integer userId) throws Exception {
//	    // Retrieve the user by userId
//	    User user = userService.findUserById(userId);
//
//	    // Check if the user exists
//	    if (user == null) {
//	        throw new NotFoundException();
//	    }
//
//	    // Check if the post exists
//	    Optional<Post> optionalPost = repo.findById(postId);
//	    if (!optionalPost.isPresent()) {
//	        throw new NotFoundException();
//	    }
//
//	    // Get the existing post
//	    Post existingPost = optionalPost.get();
//
//	    // Update the fields of the existing post if the corresponding fields in the updated post are not null
//	    if (updatedPost.getContent() != null) {
//	        existingPost.setContent(updatedPost.getContent());
//	    }
//	    
//	    if (updatedPost.getImageUrl() != null) {
//	        existingPost.setImageUrl(updatedPost.getImageUrl());
//	    }
//	    if (updatedPost.getVideo() != null) {
//	        existingPost.setVideo(updatedPost.getVideo());
//	    }
//
//	    // Save and return the updated post
//	    return repo.save(existingPost);
//	}



	

	


	

	

	

	
	
		
//		
//	@Override
//	public Post createNewPost(Post post, Integer userId, MultipartFile image) throws Exception {
//	    User user = userService.findUserById(userId);
//	    
//	    Post newPost = new Post();
//	    // Set other post details
//	    newPost.setContent(post.getContent());
//	    newPost.setShares(post.getShares());
//	    newPost.setLikes(post.getLikes());
//	   // newPost.setComments(post.getComments());
//	    newPost.setVideo(post.getVideo());
//	    newPost.setUser(user);
//	    
//	    // Set image data
//	    newPost.setImageBytes(image.getBytes());
//	    
//	    return repo.save(newPost);
//	}

	
	
}

