package com.example.demo.Service;

import java.util.List;

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

    @Override
    public Comment createComment(Comment comment, int userId, int postId) {
        User user = userRepository.findById(userId).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);

        if (user != null && post != null) {
            comment.setUser(user);
            comment.setPost(post);
            return commentRepository.save(comment);
        }

        return null; // Handle error if user or post is not found
    }
    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment updateComment(int id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment != null) {
            updatedComment.setId(id);
            return commentRepository.save(updatedComment);
        }
        return null; // Handle error if comment is not found
    }

    @Override
    public boolean deleteComment(int id) {
        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment != null) {
            commentRepository.delete(existingComment);
            return true;
        }
        return false; // Handle error if comment is not found
    }
    
}
