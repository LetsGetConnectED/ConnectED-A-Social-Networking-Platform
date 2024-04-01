package com.example.demo.Controller;
 
import java.io.IOException;
import java.sql.Blob;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.example.demo.Model.User;
import com.example.demo.Service.PostService;
import com.example.demo.Service.UserService;

 
@RestController
public class PostController {

	@Autowired
	private PostService service;
	
	
	@GetMapping("/details")
	public ResponseEntity<List<Post>> getAllPosts() {
	    List<Post> posts = service.getAllPosts();
	    return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@GetMapping("post/{id}")
	public ResponseEntity<Optional<Post>> getPostById(@PathVariable int id) {
	    Optional<Post> post = service.getPostById(id);
	    if (post.isPresent()) {
	        return new ResponseEntity<>(post, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	 @GetMapping("/user/{email}")
	    public ResponseEntity<List<Post>> findPostsByEmail(@PathVariable String email) {
	        List<Post> posts = service.getPostsByUserEmail(email);
	        return new ResponseEntity<>(posts, HttpStatus.OK);
	    }


//	
//	@PutMapping("/{id}")
//    public ResponseEntity<Post> updatePost(@RequestBody Post updatedPost, @PathVariable int id) {
//        try {
//           Post post = service.updatePost(id, updatedPost);
//            return new ResponseEntity<>(post, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//       }
//   }
//	
//	
//	 @DeleteMapping("/{id}")
//	    public ResponseEntity<Void> deletePost(@PathVariable int id) {
//	        try {
//	            service.deletePost(id);
//	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	        } catch (Exception e) {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//	    }
//	 


	 @PostMapping("/user/{email}")
	    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable String email) {
	        try {
	            Post createdPost = service.createNewPost(post, email);
	            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	 @PutMapping("/user/{email}/posts/{id}")
	    public ResponseEntity<Post> updatePost(@RequestBody Post updatedPost, @PathVariable int id, @PathVariable String email) {
	        try {
	            Post updated = service.updatePost(id, updatedPost, email);
	            return new ResponseEntity<>(updated, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 @DeleteMapping("/user/{email}/posts/{id}")
	    public ResponseEntity<String> deletePost(@PathVariable int id, @PathVariable String email) {
	        try {
	            service.deletePost(id, email);
	            String message = "Post with id " + id + " for user " + email + " deleted successfully.";
	            return new ResponseEntity<>(message, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Failed to delete post with id " + id + " for user " + email, HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 
	 
	 
	 

	 
	 
	 
	 @GetMapping("/{email}/{postId}/image")
	 public ResponseEntity<byte[]> getImageBytes(@PathVariable String email, @PathVariable int postId) {
	     byte[] imageBytes = service.getImageBytes(email, postId);
	     if (imageBytes != null) {
	         return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }

	 @PostMapping("/{email}/{postId}/image")
	 public ResponseEntity<Void> updateImageBytes(@PathVariable String email, @PathVariable int postId, @RequestParam("file") MultipartFile file) throws IOException {
	     byte[] imageBytes = file.getBytes();
	     service.updateImageBytes(email, postId, imageBytes);
	     return ResponseEntity.ok().build();
	 }

	 @PutMapping("/{email}/{postId}/image")
	 public ResponseEntity<Void> updateImageBytes1(@PathVariable String email, @PathVariable int postId, @RequestParam("file") MultipartFile file) throws IOException {
	     byte[] imageBytes = file.getBytes();
	     service.updateImageBytes(email, postId, imageBytes);
	     return ResponseEntity.ok().build();
	 }
	 @PostMapping("{email}")
	 public ResponseEntity<Post> createPost(
	         @RequestParam(value = "caption", required = false) String caption,
	         @RequestParam(value = "shares", required = false) Integer shares,
	         @RequestParam(value = "likes", required = false) Integer likes,
	         @RequestParam(value = "image", required = false) MultipartFile image,
	         @RequestParam(value = "video", required = false) MultipartFile video,
	         @PathVariable String email) {
	     try {
	         Post post = new Post();
	         post.setCaption(caption != null ? caption : "");
	         post.setShares(shares != null ? shares : 0);
	         post.setLikes(likes != null ? likes : 0);

	         if (image != null) {
	             byte[] imageBytes = image.getBytes();
	             post.setImageBytes(imageBytes);
	         }

	         if (video != null) {
	             byte[] videoBytes = video.getBytes();
	             post.setVideoBytes(videoBytes);
	         }

	         // Obtain the current date
	         Date currentDate = new Date();

	         Post createdPost = service.createNewPost(post, email, currentDate);
	         return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	     } catch (Exception e) {
	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }


//	 @PostMapping("{email}")
//	 public ResponseEntity<Post> createPost(
//	         @RequestParam(value = "caption", required = false) String caption,
//	         @RequestParam(value = "shares", required = false) Integer shares,
//	         @RequestParam(value = "likes", required = false) Integer likes,
//	         @RequestParam(value = "image", required = false) MultipartFile image,
//	         @RequestParam(value = "video", required = false) MultipartFile video,
//	         @PathVariable String email) {
//	     try {
//	         Post post = new Post();
//	         post.setCaption(caption != null ? caption : "");
//	         post.setShares(shares != null ? shares : 0);
//	         post.setLikes(likes != null ? likes : 0);
//
//	         if (image != null) {
//	             byte[] imageBytes = image.getBytes();
//	             post.setImageBytes(imageBytes);
//	         }
//
//	         if (video != null) {
//	             byte[] videoBytes = video.getBytes();
//	             post.setVideoBytes(videoBytes);
//	         }
//
//	         Post createdPost = service.createNewPost(post, email);
//	         return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
//	     } catch (Exception e) {
//	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	     }
//	 }
//
//	 
	 
	 
	 
	 
	 


	 


	

	
	 
 
}
 
 