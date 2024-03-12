package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Post;
import com.example.demo.Service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService service;
	
	 @GetMapping("/details")
	    public List<Post> getAllPosts() {
	        return service.getAllPosts();
	    }
	    
	    @GetMapping("/{id}")
	    public Optional<Post> getPostById(@PathVariable int id) {
	        return service.getPostById(id);
	    }
	    
	    @PostMapping("/post")
	    public Post createPost(@RequestBody Post post) {
	        return service.createPost(post);
	    }
	    
	    @PutMapping("/{id}")
	    public Post updatePost(@PathVariable int id, @RequestBody Post updatedPost) {
	        return service.updatePost(id, updatedPost);
	    }
	    
	    @DeleteMapping("/{id}")
	    public void deletePost(@PathVariable int id) {
	       service.deletePost(id);
	    }
}
