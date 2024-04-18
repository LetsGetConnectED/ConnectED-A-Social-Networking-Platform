package com.connected.post.dto;
public class PostLikeDTO {
    private Long userId;
    private String userEmail;
    private String likeStatus;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(String likeStatus) {
		this.likeStatus = likeStatus;
	}
	public PostLikeDTO(Long userId, String userEmail, String likeStatus) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.likeStatus = likeStatus;
	}
	@Override
	public String toString() {
		return "PostLikeDTO [userId=" + userId + ", userEmail=" + userEmail + ", likeStatus=" + likeStatus + "]";
	}
	public PostLikeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}
