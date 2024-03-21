package com.example.demo.mapper;

import com.example.demo.Model.Post;
import com.example.demo.dto.PostDTO;

public class PostMapper {
	 public static PostDTO toDTO(Post post) {
	        PostDTO dto = new PostDTO();
	        dto.setId(post.getId());
	        dto.setContent(post.getContent());
	        dto.setShares(post.getShares());
	        dto.setLikes(post.getLikes());
	        dto.setImageUrl(post.getImageUrl());
	        dto.setVideo(post.getVideo());
	        dto.setUser(post.getUser());
	        return dto;
	    }
	 public static Post toEntity(PostDTO dto) {
	        Post post = new Post();
	        post.setId(dto.getId());
	        post.setContent(dto.getContent());
	        post.setShares(dto.getShares());
	        post.setLikes(dto.getLikes());
	        post.setImageUrl(dto.getImageUrl());
	        post.setVideo(dto.getVideo());
	        post.setUser(dto.getUser());
	        return post;
	    }

}
