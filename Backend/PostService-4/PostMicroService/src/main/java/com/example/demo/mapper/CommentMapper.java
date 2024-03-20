package com.example.demo.mapper;

import com.example.demo.dto.CommentDTO;
import com.example.demo.Model.Comment;

public class CommentMapper {

	    public static Comment toEntity(CommentDTO commentDTO) {
	        Comment comment = new Comment();
	        comment.setId(commentDTO.getId());
	        comment.setComments(commentDTO.getComments());
	       
	        return comment;
	    }

	    public static CommentDTO toDTO(Comment comment) {
	        CommentDTO dto = new CommentDTO();
	        dto.setId(comment.getId());
	        dto.setComments(comment.getComments());
	        dto.setUserId(comment.getUser() != null ? comment.getUser().getId() : null);
	        dto.setPostId(comment.getPost() != null ? comment.getPost().getId() : null);
	        return dto;
	    }

	    
	}

	

	   



		

	

	


