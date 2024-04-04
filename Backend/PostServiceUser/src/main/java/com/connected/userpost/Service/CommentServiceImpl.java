package com.connected.userpost.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connected.userpost.Exception.PostNotFoundException;
import com.connected.userpost.Model.Comment;
import com.connected.userpost.Model.Post;
import com.connected.userpost.Model.User;
import com.connected.userpost.Repository.CommentRepository;
import com.connected.userpost.Repository.PostRepository;
import com.connected.userpost.Repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    
    public void saveComments(List<Comment> comments) {
        commentRepository.saveAll(comments);
    }
    @Override
    public Comment createComment(Comment comment, String senderEmail, String receiverEmail, LocalDate postDate) {
        // Retrieve or create senderUser
        User senderUser = userRepository.findByEmail(senderEmail)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(senderEmail);
                    return userRepository.save(newUser);
                });
        comment.setSenderUser(senderUser);

        // Retrieve or create receiverUser
        User receiverUser = userRepository.findByEmail(receiverEmail)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(receiverEmail);
                    return userRepository.save(newUser);
                });
        comment.setReceiverUser(receiverUser);

        // Retrieve the existing post based on the user's email and the specified date
        List<Post> posts = postRepository.findByUserEmailAndDate(receiverEmail, postDate)
                .orElseThrow(() -> new PostNotFoundException("Post not found for the specified date and receiver email"));

        // Since there can be multiple posts on the same date, choose one arbitrarily or based on your business logic
        Post post = posts.get(0); // Assuming you want the first post from the list

        // Set the post to the comment
        comment.setPost(post);

        // Save the comment
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String senderEmail, String receiverEmail, LocalDate postDate) {
        List<Comment> comments = commentRepository.findBySenderUserEmailAndReceiverUserEmailAndPostDate(senderEmail, receiverEmail, postDate);
        
        if (comments.isEmpty()) {
            throw new PostNotFoundException("Comments not found for the specified sender, receiver, and date.");
        }
        
        // Assuming you want to delete all comments found for the specified criteria
        commentRepository.deleteAll(comments);
    }
    
    @Override
    public List<Comment> getAllCommentsForPost(String receiverEmail, LocalDate postDate) {
        return commentRepository.findByReceiverUserEmailAndPostDate(receiverEmail, postDate);
    }






//    @Override
//    public Comment createComment(Comment comment, String senderEmail, String receiverEmail, LocalDate postDate) {
//        // Retrieve or create senderUser
//        User senderUser = userRepository.findByEmail(senderEmail)
//                .orElseGet(() -> {
//                    User newUser = new User();
//                    newUser.setEmail(senderEmail);
//                    return userRepository.save(newUser);
//                });
//        comment.setSenderUser(senderUser);
//
//        // Retrieve or create receiverUser
//        User receiverUser = userRepository.findByEmail(receiverEmail)
//                .orElseGet(() -> {
//                    User newUser = new User();
//                    newUser.setEmail(receiverEmail);
//                    return userRepository.save(newUser);
//                });
//        comment.setReceiverUser(receiverUser);
//
//        // Set the post date
//        if (comment.getPost() != null) {
//            comment.getPost().setDate(postDate);
//        }
//
//        return commentRepository.save(comment);
//    }

//    @Override
//    public Comment createComment(Comment comment, String email, LocalDate postDate) {
//        // Set the email for the user associated with the comment
//        User user = comment.getUser();
//        if (user != null) {
//            user.setEmail(email);
//        }
//
//        // Get the post associated with the comment
//        Post post = comment.getPost();
//        if (post != null) {
//            // Set the post date for the comment
//            post.setDate(postDate);
//        }
//
//        // Save the comment
//        return commentRepository.save(comment);
//    }
//    
//    @Override
//	public boolean deleteCommentByEmailAndDate(String email, LocalDate date) {
//		 List<Comment> commentsToDelete = commentRepository.findByUserEmailAndPostDate(email, date);
//
//	        if (commentsToDelete.isEmpty()) {
//	            // No comments found for the provided email and date
//	            return false;
//	        } else {
//	            // Delete each comment found
//	            for (Comment comment : commentsToDelete) {
//	                commentRepository.delete(comment);
//	            }
//	            return true;
//	        }
//	}


    



//    @Override
//    public Comment createComment(Comment comment, int postId, String email) {
//        Post post = postRepository.findById(postId).orElse(null);
//        User user = userRepository.findByEmail(email).orElse(null); // Retrieve User object from Optional<User>
//        if (post != null && user != null) {
//            comment.setPost(post);
//            comment.setUser(user);
//            return commentRepository.save(comment);
//        }
//        return null;
//    }

	@Override
	public List<Comment> getAllComments() {
		 return commentRepository.findAll();
	}
//	 @Override
//	    public List<Comment> getCommentsByEmailAndPostId(String email, int postId) {
//	        return commentRepository.findByUserEmailAndPostId(email, postId);
//	    }
//	 @Override
//	 public boolean updateCommentByIdAndPostIdAndEmail(int commentId, int postId, String email, Comment updatedComment) {
//	     Optional<Comment> existingCommentOptional = commentRepository.findById(commentId);
//	     if (existingCommentOptional.isPresent()) {
//	         Comment existingComment = existingCommentOptional.get();
//	         // Check if the existing comment belongs to the specified post and user
//	         if (existingComment.getPost().getId() == postId && existingComment.getUser().getEmail().equals(email)) {
//	             // Update the existing comment properties with the properties of updatedComment
//	             existingComment.setComments(updatedComment.getComments());
//	             // You can update other properties as needed
//
//	             // Save the updated comment
//	             commentRepository.save(existingComment);
//	             return true;
//	         } else {
//	             return false; // Comment does not belong to the specified post and user
//	         }
//	     } else {
//	         return false; // Comment not found with the given ID
//	     }
//	 }
//	 @Override
//	    public boolean updateCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email, Comment updatedComment) {
//	        Optional<Comment> commentOptional = commentRepository.findById(commentId);
//	        if (commentOptional.isPresent()) {
//	            Comment comment = commentOptional.get();
//	            if (comment.getPost().getId() == postId && comment.getUser().getEmail().equals(email)) {
//	                // Update comment fields
//	                comment.setComments(updatedComment.getComments());
//	                // Save the updated comment
//	                commentRepository.save(comment);
//	                return true;
//	            }
//	        }
//	        return false;
//	    }
//	 @Override
//	    public boolean deleteCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email) {
//	        Optional<Comment> commentOptional = commentRepository.findById(commentId);
//	        if (commentOptional.isPresent()) {
//	            Comment comment = commentOptional.get();
//	            // Check if the comment belongs to the specified post and user
//	            if (comment.getPost().getId() == postId && comment.getUser().getEmail().equals(email)) {
//	                commentRepository.deleteById(commentId);
//	                return true;
//	            }
//	        }
//	        return false;
//	    }






	






//	@Override
//	public boolean deleteCommentByEmailAndDate(String email, LocalDate date) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public Comment createComment(Comment comment, int postId, String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public boolean updateCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email,
//			Comment updatedComment) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean deleteCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
	
	


	  


	


}

    
    

