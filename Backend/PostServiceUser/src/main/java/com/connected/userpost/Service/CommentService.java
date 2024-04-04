package com.connected.userpost.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.connected.userpost.Model.Comment;

public interface CommentService {
   
    List<Comment> getAllComments();
//    Comment getCommentById(int id);
//    Comment updateComment(int id, Comment updatedComment);
//    boolean deleteComment(int id);
    
    
    
//	Comment createComment(Comment comment, int postId, String email);
//	List<Comment> getCommentsByEmailAndPostId(String email, int postId);
//	
//	boolean updateCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email, Comment updatedComment);
//    boolean deleteCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email);
	
    
    Comment createComment(Comment comment, String senderEmail, String receiverEmail, LocalDate postDate);
//    Comment createComment(Comment comment, String email, LocalDate postDate);
    //boolean deleteCommentByEmailAndDate(String email, LocalDate date);



	void deleteComment(String senderEmail, String receiverEmail, LocalDate postDate);
	List<Comment> getAllCommentsForPost(String receiverEmail, LocalDate postDate);
	
}



