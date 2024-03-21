package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Comment;
import com.example.demo.Repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(int id, Comment updatedComment) {
        updatedComment.setId(id);
        return commentRepository.save(updatedComment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getChildComments(Comment parentComment) {
        return commentRepository.findByParentComment(parentComment);
    }
}
