package com.connected.post.model;





import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class UserPostLike {
	@ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
	
	@Enumerated(EnumType.STRING)
	private PostLikeStatus likeStatus;

	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public PostLikeStatus getLikeStatus() {
		return likeStatus;
	}


	public void setLikeStatus(PostLikeStatus likeStatus) {
		this.likeStatus = likeStatus;
	}
	


	public UserPostLike(User user, PostLikeStatus likeStatus) {
		super();
		this.user = user;
		this.likeStatus = likeStatus;
	}
	


	@Override
	public String toString() {
		return "PostLike [user=" + user + ", likeStatus=" + likeStatus + "]";
	}


	public UserPostLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

