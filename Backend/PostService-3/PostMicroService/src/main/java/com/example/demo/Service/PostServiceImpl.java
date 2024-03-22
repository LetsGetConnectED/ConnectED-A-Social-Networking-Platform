
package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public Post updatePost(int id, Post updatedPost) {
	    updatedPost.setId(id);
	    return repo.save(updatedPost);
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

