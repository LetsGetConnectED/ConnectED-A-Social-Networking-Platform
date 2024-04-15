package com.connected.post.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.connected.post.exception.NotFoundException;
import com.connected.post.model.AdvertisementPost;
import com.connected.post.model.Advertiser;
import com.connected.post.model.AdvertiserComment;
import com.connected.post.model.User;
import com.connected.post.repository.AdvertisementPostRepository;
import com.connected.post.repository.AdvertiserRepository;
import com.connected.post.repository.UserRepository;
import com.connected.post.service.AdvertisementService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/advertisements")
@Validated
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdvertiserRepository advertiserRepository;

	@Autowired
	private AdvertisementPostRepository postRepository;

	@GetMapping
	public List<AdvertisementPost> getAllPosts() {
		return advertisementService.getAllPosts();
	}

	@GetMapping("/advertiser/{advertiserEmail}")
	public List<AdvertisementPost> getAllPostsByAdvertiserEmail(@PathVariable String advertiserEmail) {
		return advertisementService.getAllPostsByAdvertiserEmail(advertiserEmail);
	}

	@GetMapping("/advertiser/{advertiserEmail}/{postDate}")
	public ResponseEntity<AdvertisementPost> getPostByAdvertiserEmailAndDate(@PathVariable String advertiserEmail,
			@PathVariable String postDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(postDate, formatter);
			Optional<AdvertisementPost> post = advertisementService.getPostByAdvertiserEmailAndDate(advertiserEmail,
					date);
			return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		} catch (DateTimeParseException ex) {
			throw new NotFoundException("Invalid email or post date format");
		}
	}

	@DeleteMapping("/advertisement-post/{advertiserEmail}/{postDate}")
    public ResponseEntity<?> deleteAdvertisementPost(@PathVariable String advertiserEmail, @PathVariable String postDate) {
        try {
            advertisementService.deleteAdvertisementPost(advertiserEmail, postDate);
            return ResponseEntity.ok("Advertisement post deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete advertisement post: " + e.getMessage());
        }
    }
	@DeleteMapping("/advertiser/{advertiserEmail}")
	public ResponseEntity<?> deleteAllPostsByAdvertiserEmail(@PathVariable String advertiserEmail) {
		advertisementService.deleteAllPostsByAdvertiserEmail(advertiserEmail);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/advertiser/{advertiserEmail}/{postDate}")
	public ResponseEntity<?> updatePost(@PathVariable String advertiserEmail, @PathVariable String postDate,
			@Valid @RequestBody AdvertisementPost updatedPost) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(postDate, formatter);
			advertisementService.updatePost(advertiserEmail, date, updatedPost);
			return ResponseEntity.noContent().build();
		} catch (DateTimeParseException ex) {
			throw new NotFoundException("Invalid post date format");
		}
	}

	@PostMapping("/advertiser")
	public ResponseEntity<?> createAdvertisementPost(@RequestParam String advertiserEmail, @RequestParam String caption,
			@RequestParam String link, @RequestParam MultipartFile image) {
		try {
			long imageSize = image.getSize();
			
			byte[] imageBytes = image.getBytes();
			AdvertisementPost createdPost = advertisementService.createAdvertisementPost(advertiserEmail, caption, link,
					imageBytes);
			return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/like")
	public ResponseEntity<?> likePost(@RequestParam String userEmail, @RequestParam String advertiserEmail,
			@RequestParam String postDate, @RequestParam Long postId) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(postDate, formatter);

			User user = userRepository.findByEmail(userEmail)
					.orElseThrow(() -> new NotFoundException("User not found"));

			Advertiser advertiser = advertiserRepository.findByEmail(advertiserEmail)
					.orElseThrow(() -> new NotFoundException("Advertiser not found"));

			advertisementService.likePostByUserAndDate(user, advertiser, date, postId);
			return ResponseEntity.noContent().build();
		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().body("Invalid date format");
		}
	}

	@GetMapping("/like/{postId}")
	public ResponseEntity<List<String>> getLikedUsersByPostId(@PathVariable Long postId) {
		try {
			List<String> likedUsers = advertisementService.getLikedUsersByPostId(postId);
			return ResponseEntity.ok(likedUsers);
		} catch (NotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/liked-posts")
    public ResponseEntity<?> getLikedPostsByUserEmail(@RequestParam String userEmail) {
        try {
            List<Long> likedPostIds = advertisementService.getLikedPostsByUserEmail(userEmail);
            return ResponseEntity.ok(likedPostIds);
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
	@PostMapping("/share")
    public ResponseEntity<?> sharePost(@RequestParam String advertiserEmail, @RequestParam String postDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(postDate, formatter);
            advertisementService.sharePost(advertiserEmail, localDate);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format: " + postDate);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
	@PostMapping("/comment")
	public ResponseEntity<?> addComment(@RequestParam String receiverEmail, @RequestParam String senderEmail,
			@RequestParam(required = false) String commenterEmail, @RequestParam String postDate,
			@RequestParam String commentText, @RequestParam(required = false) Long parentCommentId) {
		LocalDate parsedPostDate;
		try {
			parsedPostDate = LocalDate.parse(postDate);
		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().body("Invalid post date format");
		}

		advertisementService.addCommentToPost(receiverEmail, senderEmail, commenterEmail, parsedPostDate, commentText,
				parentCommentId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam String receiverEmail, @RequestParam String senderEmail,
                                           @RequestParam String postDate, @RequestParam String commenterEmail,
                                           @RequestParam String comment) {
        try {
            LocalDate parsedPostDate = LocalDate.parse(postDate);
            advertisementService.deleteComment(receiverEmail, senderEmail, parsedPostDate, commenterEmail, comment);
            return ResponseEntity.noContent().build();
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid post date format");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<AdvertiserComment>> getAllCommentsByPostId(@PathVariable Long postId) {
		try {
			List<AdvertiserComment> comments = advertisementService.getAllCommentsByPostId(postId);
			return ResponseEntity.ok(comments);
		} catch (NotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/posts/{postId}/comments/{commentId}/replies")
	public ResponseEntity<List<AdvertiserComment>> getRepliesToComment(@PathVariable Long postId, @PathVariable Long commentId) {
		try {
			List<AdvertiserComment> replies = advertisementService.getRepliesToComment(postId, commentId);
			return ResponseEntity.ok(replies);
		} catch (NotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/comment/parent")
	public ResponseEntity<?> addParentComment(@RequestParam String receiverEmail, @RequestParam String senderEmail,
			@RequestParam Long postId, @RequestParam String postDate, @RequestParam String commentText) {
		try {
			LocalDate parsedPostDate = LocalDate.parse(postDate);
			advertisementService.addParentComment(receiverEmail, senderEmail, postId, parsedPostDate, commentText);
			return ResponseEntity.ok("Parent comment added successfully");
		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().body("Invalid post date format");
		}
	}
	
	@DeleteMapping("/advertisement-post/{postId}/comments")
	public ResponseEntity<?> deleteAllComments(@PathVariable Long postId) {
	    try {
	        advertisementService.deleteAllComments(postId);
	        return ResponseEntity.ok("All comments deleted successfully.");
	    } catch (NotFoundException e) {
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete comments: " + e.getMessage());
	    }
	}



}
