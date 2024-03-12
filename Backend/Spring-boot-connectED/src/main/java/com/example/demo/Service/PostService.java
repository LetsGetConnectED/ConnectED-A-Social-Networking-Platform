package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Post;
import com.example.demo.Repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	 public List<Post> getAllPosts() {
	        return repo.findAll();
	    }
	    
	    public Optional<Post> getPostById(int id) {
	        return repo.findById(id);
	    }
	    
	    public Post createPost(Post post) {
	        return repo.save(post);
	    }
	    
	    public Post updatePost(int id, Post updatedPost) {
	        updatedPost.setId(id);
	        return repo.save(updatedPost);
	    }
	    
	    public void deletePost(int id) {
	       repo.deleteById(id);
	    }
}
