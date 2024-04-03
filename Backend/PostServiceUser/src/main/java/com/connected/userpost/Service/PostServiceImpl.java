
package com.connected.userpost.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connected.userpost.Exception.PostNotFoundException;
import com.connected.userpost.Exception.UserNotFoundException;
import com.connected.userpost.Model.Post;
import com.connected.userpost.Model.User;
import com.connected.userpost.Repository.PostRepository;
import com.connected.userpost.Repository.UserRepository;



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
    public List<Post> getPostsByUserEmail(String email) {
        return repo.findByUserEmail(email);
    }

    @Override
    public Optional<List<Post>> getPostsByEmailAndDate(String email, LocalDate date) {
        return repo.findByUserEmailAndDate(email, date);
    }

    @Override
    public Post createNewPost(Post post, String email, LocalDate date) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            post.setUser(user);
            post.setDate(date); // Update to use LocalDate
            return repo.save(post);
        } else {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
    }


    @Override
    public void likePostByEmailAndDate(String email, LocalDate date) {
        List<Post> posts = repo.findByUserEmailAndDate(email, date)
                                .orElseThrow(() -> new PostNotFoundException("Post not found."));
        // Perform like operation on the post(s)
        for (Post post : posts) {
            post.setLikes(post.getLikes() + 1);
            repo.save(post);
        }
    }

    @Override
    public void sharePostByEmailAndDate(String email, LocalDate date) {
        List<Post> posts = repo.findByUserEmailAndDate(email, date)
                                .orElseThrow(() -> new PostNotFoundException("Post not found."));
        // Perform share operation on the post(s)
        for (Post post : posts) {
            post.setShares(post.getShares() + 1);
            repo.save(post);
        }
    }


    @Override
    public void deletePostsByEmail(String email) {
        repo.deleteByUserEmail(email);
    }
    @Override
    public void deletePostsByEmailAndDate(String email, LocalDate date) {
        // Retrieve posts based on email and date
        List<Post> posts = repo.findAllByUserEmailAndDate(email, date);

        // Delete each post
        for (Post post : posts) {
            repo.delete(post);
        }
    }

	
//	@Override
//	public List<Post> getAllPosts() {
//	    return repo.findAll();
//	}
//	 @Override
//	    public List<Post> getPostsByUserEmail(String email) {
//	        Optional<User> userOptional = userRepository.findByEmail(email);
//	        if (userOptional.isPresent()) {
//	            User user = userOptional.get();
//	            return repo.findByUser(user);
//	        } else {
//	            // Handle the case where user is not found
//	            return null; // Or throw an exception or return an empty list
//	        }
//	    }
//	 @Override
//	 public Post createNewPost(Post post, String email, LocalDateTime currentDateTime) throws Exception {
//	     Optional<User> userOptional = userRepository.findByEmail(email);
//	     if (userOptional.isPresent()) {
//	         User user = userOptional.get();
//	         post.setUser(user);
//	         post.setDate(currentDateTime.toLocalDate()); // Convert LocalDateTime to LocalDate
//	         return repo.save(post);
//	     } else {
//	         throw new UserNotFoundException("User with email " + email + " not found");
//	     }
//	 }
//
//	 
////	 @Override
////	    public Post createNewPost(Post post, String email, LocalDateTime currentDateTime) throws Exception {
////	        Optional<User> userOptional = userRepository.findByEmail(email);
////	        if (userOptional.isPresent()) {
////	            User user = userOptional.get();
////	            post.setUser(user);
////	            post.setDate(currentDateTime);
////	            return repo.save(post);
////	        } else {
////	            throw new UserNotFoundException("User with email " + email + " not found");
////	        }
////	    }
//	 
//
//	 
//
//	 @Override
//	 public void deletePostsByEmail(String email) throws PostNotFoundException {
//	     List<Post> postsToDelete = repo.findByUserEmail(email);
//	     if (postsToDelete.isEmpty()) {
//	         throw new PostNotFoundException("No posts found for user with email: " + email);
//	     }
//	     repo.deleteAll(postsToDelete);
//	 }
//	
//
//
//
//
//	 
//
//	 
//	 
//	@Override
//	public Optional<Post> getPostById(int id) {
//	    return repo.findById(id);
//	}
//	@Override
//    public Post updatePost(int id, Post updatedPost, String email) throws Exception {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isPresent()) {
//            Optional<Post> postOptional = repo.findByIdAndUserEmail(id, email);
//            if (postOptional.isPresent()) {
//                Post post = postOptional.get();
//                // Update post properties only if the updated data is not null
//                if (updatedPost.getCaption() != null) {
//                    post.setCaption(updatedPost.getCaption());
//                }
//                if (updatedPost.getShares() != null) {
//                    post.setShares(updatedPost.getShares());
//                }
//                if (updatedPost.getLikes() != null) {
//                    post.setLikes(updatedPost.getLikes());
//                }
//                
//                // Save the updated post
//                return repo.save(post);
//            } else {
//                throw new PostNotFoundException("Post not found with id: " + id);
//            }
//        } else {
//            throw new Exception("User not found with email: " + email);
//        }
//    }
//	
//	
//	
//		
//	
//	
//	
//	
//	 
//	
//
//	 @Override
//	 public void updateImageBytes(String email, int postId, byte[] imageBytes) {
//	     Optional<Post> postOptional = repo.findByUserEmailAndId(email, postId);
//	     if (postOptional.isPresent()) {
//	         Post post = postOptional.get();
//	         post.setImageBytes(imageBytes);
//	         repo.save(post);
//	     } else {
//	         throw new PostNotFoundException("Post not found with id: " + postId + " and email: " + email);
//	     }
//	 }
//	 
//	 
//	 
//	 
//	
//	 
//		
//		@Override
//		public Post updatePostByEmail(Post updatedPost, String email) throws Exception {
//			 Optional<User> userOptional = userRepository.findByEmail(email);
//		        if (userOptional.isPresent()) {
//		            User user = userOptional.get();
//		            updatedPost.setUser(user);
//		            return repo.save(updatedPost);
//		        } else {
//		            throw new UserNotFoundException("User not found with email: " + email);
//		        }
//		}
//
//		@Override
//		public Post updatePost(Post updatedPost, String email) throws Exception {
//			
//			        Optional<User> userOptional = userRepository.findByEmail(email);
//			        if (userOptional.isPresent()) {
//			            User user = userOptional.get();
//			            updatedPost.setUser(user);
//			            return repo.save(updatedPost);
//			        } else {
//			            throw new UserNotFoundException("User not found with email: " + email);
//			        }
//			    }
//		
//		
//		
//		 public Post savePost(Post post) {
//		        return repo.save(post);
//		    }
//		 @Override
//		 public void deletePostsByEmailAndDate(String email, Date date) throws PostNotFoundException {
//		     // Find posts by email and date
//		     List<Post> postsToDelete = repo.findAllByUserEmailAndDate(email, date);
//		     
//		     // Check if any posts are found
//		     if (postsToDelete.isEmpty()) {
//		         throw new PostNotFoundException("No posts found for user with email: " + email + " and date: " + date);
//		     }
//		     
//		     // Delete each post individually
//		     for (Post post : postsToDelete) {
//		         repo.delete(post);
//		     }
//		 }
//
//		 @Override
//		    public Optional<List<Post>> getPostsByEmailAndDate(String email, LocalDateTime date) {
//		        List<Post> posts = repo.findAllByUserEmailAndDate(email, date);
//		        return Optional.ofNullable(posts);
//		    }
//		 
//		 @Override
//		 public void likePostByEmailAndDate(String email, LocalDateTime date) {
//		     // Find the AdvertisementPost based on email and date
//		     List<Post> posts = repo.findByUserEmailAndDate(email, date);
//
//		     if (!posts.isEmpty()) {
//		         Post post = posts.get(0); // Get the first post from the list
//		         // Increment the likes for the found post
//		         post.setLikes(post.getLikes() + 1);
//		         // Save the updated post
//		         repo.save(post);
//		     } else {
//		         throw new PostNotFoundException("No post found for email: " + email + " and date: " + date);
//		     }
//		 }
//		 @Override
//		    public void sharePostByEmailAndDate(String email, LocalDateTime date) {
//		        // Find the post based on email and date
//		        List<Post> posts = repo.findByUserEmailAndDate(email, date);
//
//		        if (!posts.isEmpty()) {
//		            Post post = posts.get(0); // Get the first post from the list
//		            // Increment the shares for the found post
//		            post.setShares(post.getShares() + 1);
//		            // Save the updated post
//		            repo.save(post);
//		        } else {
//		            throw new PostNotFoundException("No post found for email: " + email + " and date: " + date);
//		        }
//		    }
//		 
//		 @Override
//		    public void deletePostsByEmailAndDate(String email, LocalDateTime date) {
//		        // Retrieve posts based on email and date
//		        List<Post> posts = repo.findByUserEmailAndDate(email, date);
//
//		        // Delete each post
//		        for (Post post : posts) {
//		            repo.delete(post);
//		        }
//		    }
//
//
//
//
//		////////
//		 
//		 
////		 @Override
////		    public List<Post> getPostsByEmailAndDate(String email, Date date) {
////		        try {
////		            // Log input parameters
////		            System.out.println("Email: " + email);
////		            System.out.println("Date: " + date);
////		            
////		            // Call the repository method to fetch posts by email and date
////		            List<Post> posts = repo.findAllByUserEmailAndDate(email, date);
////		            
////		            // Log fetched posts
////		            System.out.println("Fetched Posts: " + posts);
////		            
////		            return posts;
////		        } catch (Exception ex) {
////		            // Log exception
////		            ex.printStackTrace();
////		            throw new RuntimeException("Failed to fetch posts by email and date", ex);
////		        }
////		    }
//
//		
//	
//	
//		
////		@Override                 ( ------working--------_)
////		    public void updatePostsByDate(LocalDateTime dateTime, Post updatedPost) {
////		        // Fetch posts for the given date/time
////		        List<Post> postsToUpdate = repo.findByDate(dateTime);
////
////		        // Iterate over each post and update as needed
////		        for (Post post : postsToUpdate) {
////		            // Update caption if it's provided in the updated post
////		            if (updatedPost.getCaption() != null) {
////		                post.setCaption(updatedPost.getCaption());
////		            }
////
////		            // Update likes count if it's provided in the updated post
////		            if (updatedPost.getLikes() != null) {
////		                post.setLikes(updatedPost.getLikes());
////		            }
////
////		            // Save the updated post
////		            repo.save(post);
////		        }
////		    }
//
//	
//		@Override
//	    public void deletePostsByDate(LocalDateTime dateTime) {
//	        // Fetch posts for the given date/time
//	        List<Post> postsToDelete = repo.findByDate(dateTime);
//
//	        // Delete each post individually
//	        for (Post post : postsToDelete) {
//	            repo.delete(post);
//	        }
//	    }
//
//   
//
//		@Override
//		public void updatePostsByEmailAndDate(String email, LocalDateTime dateTime, Post updatedPost) {
//		    // Fetch posts for the given email and date/time
//		    List<Post> postsToUpdate = repo.findByUserEmailAndDate(email, dateTime);
//
//		    // Iterate over each post and update as needed
//		    for (Post post : postsToUpdate) {
//		        // Update caption if it's provided in the updated post
//		        if (updatedPost.getCaption() != null) {
//		            post.setCaption(updatedPost.getCaption());
//		        }
//
//		        // Update likes count if it's provided in the updated post
//		        if (updatedPost.getLikes() != null) {
//		            post.setLikes(updatedPost.getLikes());
//		        }
//
//		        // Save the updated post
//		        repo.save(post);
//		    }
//		}
//		
//		
		
		
		    }




		
	


		


		
		
	 


	 
	 
	 
	 


	


	
	
	
	
	


