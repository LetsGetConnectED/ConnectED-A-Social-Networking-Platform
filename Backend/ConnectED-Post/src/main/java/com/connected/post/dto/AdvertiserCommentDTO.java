package com.connected.post.dto;
public class AdvertiserCommentDTO {
    private Long id;
    private Long parentCommentId;
    private String comment;
    private String receiverEmail;
    private String senderEmail;
    private Long postId;
    private String commenterEmail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentCommentId() {
		return parentCommentId;
	}
	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getCommenterEmail() {
		return commenterEmail;
	}
	public void setCommenterEmail(String commenterEmail) {
		this.commenterEmail = commenterEmail;
	}
	public AdvertiserCommentDTO(Long id, Long parentCommentId, String comment, String receiverEmail, String senderEmail,
			Long postId, String commenterEmail) {
		super();
		this.id = id;
		this.parentCommentId = parentCommentId;
		this.comment = comment;
		this.receiverEmail = receiverEmail;
		this.senderEmail = senderEmail;
		this.postId = postId;
		this.commenterEmail = commenterEmail;
	}
	@Override
	public String toString() {
		return "AdvertiserCommentDTO [id=" + id + ", parentCommentId=" + parentCommentId + ", comment=" + comment
				+ ", receiverEmail=" + receiverEmail + ", senderEmail=" + senderEmail + ", postId=" + postId
				+ ", commenterEmail=" + commenterEmail + "]";
	}
	public AdvertiserCommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
