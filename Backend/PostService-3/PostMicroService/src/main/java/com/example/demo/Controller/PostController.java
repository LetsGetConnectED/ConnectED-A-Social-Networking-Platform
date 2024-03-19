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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.Post;
import com.example.demo.Service.PostService;

 
@RestController
public class PostController {
	@Autowired
	private PostService service;
//	 @Autowired
//	    private UserService userService;
	
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

//   @PostMapping("/post")
//	public ResponseEntity<Post> createPost(@RequestBody Post post) {
//	    Post createdPost = service.createPost(post);
//	    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
//	}
 
	
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
	 
	 @GetMapping("/posts/user/{userId}")
		public ResponseEntity<List<Post>>findUsersPost(@PathVariable Integer userId){
		     List<Post> posts = service.findPostByUserId(userId);
		     return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
			
		}
	 @PostMapping("/posts/user/{userId}")
		public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception{
			Post createdPost = service.createNewPost(post,userId);
			return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
			
		}
	 
//	 @PostMapping("/posts/users/{userId}")
//	 public ResponseEntity<Post> createPost(@RequestBody Post post,
//	                                        @RequestParam("image") MultipartFile image,
//	                                        @PathVariable Integer userId) {
//	     try {
//	         // Read image data and set it to post
//	         post.setImageBytes(image.getBytes());
//	         // Call the service method to create the post
//	         Post createdPost = service.createNewPost(post, userId, image);
//	         return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
//	     } catch (Exception e) {
//	         e.printStackTrace();
//	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	     }
//	 }
	
	

	
	 
 
}
 
 