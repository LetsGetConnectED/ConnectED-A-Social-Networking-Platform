package com.connected.post.model;





import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class UserPostLike {
//	@ManyToOne
//    @JoinColumn(name = "user_email", referencedColumnName = "email")
//    private User user;
	 private String userEmail;
	
	@Enumerated(EnumType.STRING)
	private PostLikeStatus likeStatus;

	
	
	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public PostLikeStatus getLikeStatus() {
		return likeStatus;
	}



	public void setLikeStatus(PostLikeStatus likeStatus) {
		this.likeStatus = likeStatus;
	}
	



	public UserPostLike(String userEmail, PostLikeStatus likeStatus) {
		super();
		this.userEmail = userEmail;
		this.likeStatus = likeStatus;
	}
	



	@Override
	public String toString() {
		return "UserPostLike [userEmail=" + userEmail + ", likeStatus=" + likeStatus + "]";
	}



	public UserPostLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

