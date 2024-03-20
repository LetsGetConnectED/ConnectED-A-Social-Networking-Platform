package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Comment;

public interface CommentService {
	 Comment createComment(Comment comment, int userId, int postId);
    List<Comment> getAllComments();
    Comment getCommentById(int id);
//    Comment updateComment(int id, Comment updatedComment);
    boolean deleteComment(int id);
	Comment updateComment(int id, Comment updatedComment, int postId);
	
	
}



