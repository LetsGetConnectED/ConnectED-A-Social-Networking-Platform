package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Comment;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.UserRepository;

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
    public Comment createComment(Comment comment, int postId, String email) {
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findByEmail(email).orElse(null); // Retrieve User object from Optional<User>
        if (post != null && user != null) {
            comment.setPost(post);
            comment.setUser(user);
            return commentRepository.save(comment);
        }
        return null;
    }

	@Override
	public List<Comment> getAllComments() {
		 return commentRepository.findAll();
	}
	 @Override
	    public List<Comment> getCommentsByEmailAndPostId(String email, int postId) {
	        return commentRepository.findByUserEmailAndPostId(email, postId);
	    }
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
	 @Override
	    public boolean updateCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email, Comment updatedComment) {
	        Optional<Comment> commentOptional = commentRepository.findById(commentId);
	        if (commentOptional.isPresent()) {
	            Comment comment = commentOptional.get();
	            if (comment.getPost().getId() == postId && comment.getUser().getEmail().equals(email)) {
	                // Update comment fields
	                comment.setComments(updatedComment.getComments());
	                // Save the updated comment
	                commentRepository.save(comment);
	                return true;
	            }
	        }
	        return false;
	    }
	 @Override
	    public boolean deleteCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email) {
	        Optional<Comment> commentOptional = commentRepository.findById(commentId);
	        if (commentOptional.isPresent()) {
	            Comment comment = commentOptional.get();
	            // Check if the comment belongs to the specified post and user
	            if (comment.getPost().getId() == postId && comment.getUser().getEmail().equals(email)) {
	                commentRepository.deleteById(commentId);
	                return true;
	            }
	        }
	        return false;
	    }


	  


	


}

    
    

