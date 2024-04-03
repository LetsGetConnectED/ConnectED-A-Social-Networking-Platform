package com.connected.userpost.Controller;
 
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.connected.userpost.Model.Post;
import com.connected.userpost.Repository.PostRepository;
import com.connected.userpost.Service.PostService;

 

@CrossOrigin
@RestController
public class PostController {

	@Autowired
	private PostService service;
	
	@Autowired
	private PostRepository repo; 
	
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

	    @GetMapping("/user/{email}/date/{date}")
	    public ResponseEntity<Optional<List<Post>>> findPostsByEmailAndDate(
	            @PathVariable String email,
	            @PathVariable String date) {
	        try {
	            // Parse the date string to LocalDate
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate localDate = LocalDate.parse(date, formatter);

	            Optional<List<Post>> posts = service.getPostsByEmailAndDate(email, localDate);
	            return new ResponseEntity<>(posts, HttpStatus.OK);
	        } catch (DateTimeParseException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }

	    @PostMapping("/{email}")
	    public ResponseEntity<Post> createPost(
	            @RequestParam(value = "caption", required = false) String caption,
	            @RequestParam(value = "shares", defaultValue = "0") Integer shares,
	            @RequestParam(value = "likes", defaultValue = "0") Integer likes,

	            @RequestParam(value = "image", required = false) MultipartFile image,
	            @RequestParam(value = "video", required = false) MultipartFile video,
	            @PathVariable String email) {
	        try {
	            Post post = new Post();
	            post.setCaption(caption);
	            post.setShares(shares);
	            post.setLikes(likes);

	            if (image != null && !image.isEmpty()) {
	                byte[] imageBytes = image.getBytes();
	                post.setImageBytes(imageBytes);
	            }

	            if (video != null && !video.isEmpty()) {
	                byte[] videoBytes = video.getBytes();
	                post.setVideoBytes(videoBytes);
	            }

	            LocalDate currentDate = LocalDate.now(); // Get the current date

	            Post createdPost = service.createNewPost(post, email, currentDate); // Pass the current date
	            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

	        } catch (IOException e) {
	            e.printStackTrace(); // Log the error for debugging purposes
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception ex) {
	            ex.printStackTrace(); // Log the exception for debugging purposes
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/like")
	    public ResponseEntity<?> likePost(@RequestParam String email, @RequestParam String date) {
	        try {
	            // Parse the date string to LocalDate
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate localDate = LocalDate.parse(date, formatter);

	            service.likePostByEmailAndDate(email, localDate);
	            return ResponseEntity.noContent().build();
	        } catch (DateTimeParseException e) {
	            return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
	        }
	    }

	    @PostMapping("/shares")
	    public ResponseEntity<?> sharePost(@RequestParam String email, @RequestParam String date) {
	        try {
	            // Parse the date string to LocalDate
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate localDate = LocalDate.parse(date, formatter);

	            service.sharePostByEmailAndDate(email, localDate);
	            return ResponseEntity.noContent().build();
	        } catch (DateTimeParseException e) {
	            return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
	        }
	    }

	    @DeleteMapping("/user/{email}")
	    public ResponseEntity<String> deletePostsByEmail(@PathVariable String email) {

	        try {
	            service.deletePostsByEmail(email);
	            String message = "All posts for user " + email + " deleted successfully.";
	            return new ResponseEntity<>(message, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Failed to delete posts for user " + email, HttpStatus.NOT_FOUND);
	        }
	    }
	    @DeleteMapping("/user/{email}/date/{date}")
	    public ResponseEntity<String> deletePostsByEmailAndDate(@PathVariable String email, @PathVariable String date) {
	        try {
	            // Parse the date string to LocalDate
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate localDate = LocalDate.parse(date, formatter);

	            service.deletePostsByEmailAndDate(email, localDate);
	            String message = "All posts for user " + email + " on date " + date + " deleted successfully.";
	            return new ResponseEntity<>(message, HttpStatus.OK);
	        } catch (DateTimeParseException e) {
	            return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
	        } catch (Exception ex) {
	            return new ResponseEntity<>("Failed to delete posts for user " + email + " on date " + date, HttpStatus.NOT_FOUND);
	        }
	    }

	    
	
//	@GetMapping("/details")
//	public ResponseEntity<List<Post>> getAllPosts() {
//	    List<Post> posts = service.getAllPosts();
//	    return new ResponseEntity<>(posts, HttpStatus.OK);
//	}
//	
//	@GetMapping("post/{id}")
//	public ResponseEntity<Optional<Post>> getPostById(@PathVariable int id) {
//	    Optional<Post> post = service.getPostById(id);
//	    if (post.isPresent()) {
//	        return new ResponseEntity<>(post, HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	}
//	 @GetMapping("/user/{email}")
//	    public ResponseEntity<List<Post>> findPostsByEmail(@PathVariable String email) {
//	        List<Post> posts = service.getPostsByUserEmail(email);
//	        return new ResponseEntity<>(posts, HttpStatus.OK);
//	    }

//
//	
//
//
//	 
//
//	 
//	
//
//	 @PostMapping("/{email}")
//	 public ResponseEntity<Post> createPost(
//	     @RequestParam(value = "caption", required = false) String caption,
//	     @RequestParam(value = "shares", defaultValue = "0") Integer shares,
//	     @RequestParam(value = "likes", defaultValue = "0") Integer likes,
//	     
//	     @RequestParam(value = "image", required = false) MultipartFile image,
//	     @RequestParam(value = "video", required = false) MultipartFile video,
//	     @PathVariable String email) {
//	     try {
//	         Post post = new Post();
//	         post.setCaption(caption);
//	         post.setShares(shares);
//	         post.setLikes(likes);
//
//	         if (image != null && !image.isEmpty()) {
//	             byte[] imageBytes = image.getBytes();
//	             post.setImageBytes(imageBytes);
//	         }
//
//	         if (video != null && !video.isEmpty()) {
//	             byte[] videoBytes = video.getBytes();
//	             post.setVideoBytes(videoBytes);
//	         }
//	         
//	         LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current date and time
//
//	         Post createdPost = service.createNewPost(post, email, currentDateTime); // Pass the current date and time
//	         return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
//
//	     } catch (IOException e) {
//	         e.printStackTrace(); // Log the error for debugging purposes
//	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	     } catch (Exception ex) {
//	         ex.printStackTrace(); // Log the exception for debugging purposes
//	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	     }
//	 }
//	 
//	 @PostMapping("/like")
//	 public ResponseEntity<?> likePost(@RequestParam String email, @RequestParam String date) {
//	     try {
//	         // Parse the date string to LocalDateTime
//	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//	         LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
//
//	         service.likePostByEmailAndDate(email, localDateTime);
//	         return ResponseEntity.noContent().build();
//	     } catch (DateTimeParseException e) {
//	         return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
//	     }
//	 }
//	
//	 @PostMapping("/shares")
//	 public ResponseEntity<?> sharePost(@RequestParam String email, @RequestParam String date) {
//	     try {
//	         // Parse the date string to LocalDateTime
//	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//	         LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
//
//	         service.sharePostByEmailAndDate(email, localDateTime);
//	         return ResponseEntity.noContent().build();
//	     } catch (DateTimeParseException e) {
//	         return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
//	     }
//	 }
//	
//
//	@DeleteMapping("/user/{email}")
//	    public ResponseEntity<String> deletePostsByEmail(@PathVariable String email) {
//		  
//	        try {
//	            service.deletePostsByEmail(email);
//	            String message = "All posts for user " + email + " deleted successfully.";
//	            return new ResponseEntity<>(message, HttpStatus.OK);
//	        } catch (Exception e) {
//	            return new ResponseEntity<>("Failed to delete posts for user " + email, HttpStatus.NOT_FOUND);
//	        }
//	    }
//	 
//	 @DeleteMapping("/user/{email}/date/{date}")
//	 public ResponseEntity<String> deletePostsByEmailAndDate(@PathVariable String email, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
//	     try {
//	         service.deletePostsByEmailAndDate(email, date);
//	         String message = "All posts for user " + email + " on date " + date + " deleted successfully.";
//	         return new ResponseEntity<>(message, HttpStatus.OK);
//	     } catch (PostNotFoundException e) {
//	         return new ResponseEntity<>("No posts found for user " + email + " on date " + date, HttpStatus.NOT_FOUND);
//	     }
//	 }
//
////
////	 @PutMapping("/updatePostsByDate/{date}")          (-----working-----)
////	 public ResponseEntity<String> updatePostsByDate(
////	         @PathVariable String date,
////	         @RequestBody Post updatedPost) {
////	     try {
////	         // Parse the date string to LocalDateTime
////	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
////	         LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
////
////	         // Call the service method with the parsed LocalDateTime object
////	         service.updatePostsByDate(localDateTime, updatedPost);
////
////	         return new ResponseEntity<>("Posts updated successfully for date: " + date, HttpStatus.OK);
////	     } catch (DateTimeParseException e) {
////	         return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
////	     } catch (PostNotFoundException e) {
////	         return new ResponseEntity<>("No posts found for date: " + date, HttpStatus.NOT_FOUND);
////	     }
////	 }
//	 
//	 
//	// http://localhost:8080/deletePostsByDate/2024-03-29 17:43:04.596871
//	     @DeleteMapping("/deletePostsByDate/{date}")
//	     public ResponseEntity<String> deletePostsByDate(@PathVariable String date) {
//	         try {
//	             // Parse the date string to LocalDateTime
//	             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//	             LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
//
//	             // Call the service method to delete posts for the provided date
//	             service.deletePostsByDate(localDateTime);
//
//	             return new ResponseEntity<>("Posts deleted successfully for date: " + date, HttpStatus.OK);
//	         } catch (DateTimeParseException e) {
//	             return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
//	         }
//	     }
//	     
//	     @DeleteMapping("/deletePostsByDateAndEmail/{email}/{date}")
//	     public ResponseEntity<String> deletePostsByDateAndEmail(@PathVariable String email, @PathVariable String date) {
//	         try {
//	             // Parse the date string to LocalDateTime
//	             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//	             LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
//
//	             // Call the service method to delete posts for the provided email and date
//	             service.deletePostsByEmailAndDate(email, localDateTime);
//
//	             return new ResponseEntity<>("Posts deleted successfully for email: " + email + " and date: " + date, HttpStatus.OK);
//	         } catch (DateTimeParseException e) {
//	             return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
//	         }
//	     }
//	     
//	 // http://localhost:8080/updatePostsByEmailAndDate/madhu123@gmail.com/2024-03-29 17:43:04.596871
//	     @PutMapping("/updatePostsByEmailAndDate/{email}/{date}")
//	     public ResponseEntity<String> updatePostsByEmailAndDate(
//	             @PathVariable String email,
//	             @PathVariable String date,
//	             @RequestBody Post updatedPost) {
//	         try {
//	             // Parse the date string to LocalDateTime
//	             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//	             LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
//
//	             // Call the service method with the parsed LocalDateTime object
//	             service.updatePostsByEmailAndDate(email, localDateTime, updatedPost);
//
//	             return new ResponseEntity<>("Posts updated successfully for email: " + email + " and date: " + date, HttpStatus.OK);
//	         } catch (DateTimeParseException e) {
//	             return new ResponseEntity<>("Invalid date format: " + date, HttpStatus.BAD_REQUEST);
//	         } catch (PostNotFoundException e) {
//	             return new ResponseEntity<>("No posts found for email: " + email + " and date: " + date, HttpStatus.NOT_FOUND);
//	         }
//	     }
}
