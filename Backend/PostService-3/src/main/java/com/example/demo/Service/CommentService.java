package com.example.demo.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.Model.Comment;

public interface CommentService {
    List<Comment> getAllComments();
    Optional<Comment> getCommentById(int id);
    Comment createComment(Comment comment);
    Comment updateComment(int id, Comment updatedComment);
    void deleteComment(int id);
    List<Comment> getChildComments(Comment parentComment);
}

