package com.example.demo.Controller;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.demo.Model.Post;
import com.example.demo.Service.PostService;
 
@RestController
public class PostController {
	@Autowired
	private PostService service;
	@GetMapping("/details")
	public ResponseEntity<List<Post>> getAllPosts() {
	    List<Post> posts = service.getAllPosts();
	    return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Post>> getPostById(@PathVariable int id) {
	    Optional<Post> post = service.getPostById(id);
	    if (post.isPresent()) {
	        return new ResponseEntity<>(post, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

   @PostMapping("/post")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {
	    Post createdPost = service.createPost(post);
	    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}
 
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post updatedPost) {
	    Post post = service.updatePost(id, updatedPost);
	    if (post != null) {
	        return new ResponseEntity<>(post, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletePost(@PathVariable int id) {
	        try {
	            service.deletePost(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
 
}
 
 