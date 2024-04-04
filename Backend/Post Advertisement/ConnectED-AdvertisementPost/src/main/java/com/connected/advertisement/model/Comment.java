package com.connected.advertisement.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Comment {

    private String commentText;
    private String commenterName;
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
	@Override
	public String toString() {
		return "Comment [commentText=" + commentText + ", commenterName=" + commenterName + "]";
	}
	public Comment(String commentText, String commenterName) {
		super();
		this.commentText = commentText;
		this.commenterName = commenterName;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	

    // Getters and setters
}
