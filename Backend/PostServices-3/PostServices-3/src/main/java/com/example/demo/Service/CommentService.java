package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Comment;

public interface CommentService {
   
    List<Comment> getAllComments();

	Comment createComment(Comment comment, int postId, String email);
	List<Comment> getCommentsByEmailAndPostId(String email, int postId);
	
	boolean updateCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email, Comment updatedComment);
    boolean deleteCommentByCommentIdAndPostIdAndEmail(int commentId, int postId, String email);
	
}



