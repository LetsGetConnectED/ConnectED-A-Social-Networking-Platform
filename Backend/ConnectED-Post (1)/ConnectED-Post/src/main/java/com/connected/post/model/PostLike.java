package com.connected.post.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PostLike {
	@ManyToOne
	@JoinColumn(name = "user_email", referencedColumnName = "email")
	private User user;
	
	
	@Enumerated(EnumType.STRING)
	private PostLikeStatus likeStatus;

	public PostLikeStatus getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(PostLikeStatus likeStatus) {
		this.likeStatus = likeStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PostLike [user=" + user + ", likeStatus=" + likeStatus + "]";
	}

	 public PostLike(User user, AdvertisementPost post, PostLikeStatus likeStatus) {
	        this.user = user;
//	        this.post = post;
	        this.likeStatus = likeStatus;
	    }

	public PostLike() {
		// Default constructor
	}

	
}
