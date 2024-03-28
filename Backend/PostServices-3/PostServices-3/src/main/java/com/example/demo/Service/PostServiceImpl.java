
package com.example.demo.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.PostNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
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
	    public List<Post> getPostsByUserEmail(String email) {
	        Optional<User> userOptional = userRepository.findByEmail(email);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            return repo.findByUser(user);
	        } else {
	            // Handle the case where user is not found
	            return null; // Or throw an exception or return an empty list
	        }
	    }

	    @Override
	    public Post createNewPost(Post post, String email) throws Exception {
	        Optional<User> userOptional = userRepository.findByEmail(email);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            post.setUser(user);
	            return repo.save(post);
	        } else {
	            throw new Exception("User with email " + email + " not found");
	        }
	    }
	
	@Override
	public Optional<Post> getPostById(int id) {
	    return repo.findById(id);
	}
	@Override
    public Post updatePost(int id, Post updatedPost, String email) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Optional<Post> postOptional = repo.findByIdAndUserEmail(id, email);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                // Update post properties only if the updated data is not null
                if (updatedPost.getCaption() != null) {
                    post.setCaption(updatedPost.getCaption());
                }
                if (updatedPost.getShares() != null) {
                    post.setShares(updatedPost.getShares());
                }
                if (updatedPost.getLikes() != null) {
                    post.setLikes(updatedPost.getLikes());
                }
                // Save the updated post
                return repo.save(post);
            } else {
                throw new PostNotFoundException("Post not found with id: " + id);
            }
        } else {
            throw new Exception("User not found with email: " + email);
        }
    }
	 @Override
	    public void deletePost(int id, String email) throws Exception {
	        Optional<User> userOptional = userRepository.findByEmail(email);
	        if (userOptional.isPresent()) {
	            Optional<Post> postOptional = repo.findByIdAndUserEmail(id, email);
	            if (postOptional.isPresent()) {
	                repo.deleteById(id);
	            } else {
	                throw new PostNotFoundException("Post not found with id: " + id);
	            }
	        } else {
	            throw new Exception("User not found with email: " + email);
	        }
	    }
	
	
	 
	 
	 @Override
	 public byte[] getImageBytes(String email, int postId) {
	     Optional<Post> postOptional = repo.findByUserEmailAndId(email, postId);
	     if (postOptional.isPresent()) {
	         Post post = postOptional.get();
	         return post.getImageBytes();
	     } else {
	         throw new PostNotFoundException("Post not found with id: " + postId + " and email: " + email);
	     }
	 }

	 @Override
	 public void updateImageBytes(String email, int postId, byte[] imageBytes) {
	     Optional<Post> postOptional = repo.findByUserEmailAndId(email, postId);
	     if (postOptional.isPresent()) {
	         Post post = postOptional.get();
	         post.setImageBytes(imageBytes);
	         repo.save(post);
	     } else {
	         throw new PostNotFoundException("Post not found with id: " + postId + " and email: " + email);
	     }
	 }

	
	
	
	
}

