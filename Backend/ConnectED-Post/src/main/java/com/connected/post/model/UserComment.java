
package com.connected.post.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserComment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id; 

	    @Column(name = "parent_comment_id")
	    private Long parentCommentId;

	    private String comments;

	    @ManyToOne
	    @JoinColumn(name = "sender_email", referencedColumnName = "email") // Changed to sender_email
	    private User senderUser;

	    @ManyToOne
	    @JoinColumn(name = "receiver_email", referencedColumnName = "email") // Changed to receiver_email
	    private User receiverUser;

	    @ManyToOne
	    @JoinColumn(name = "post_date", referencedColumnName = "date") 
	    private UserPost post;

	    @ManyToOne
	    @JoinColumn(name = "post_id", referencedColumnName = "id")
	    private UserPost postId;

	    @Column
	    private String commenterEmail;
	    
	    


	public int getId() {
			return id;
		}




		public void setId(int id) {
			this.id = id;
		}




		public Long getParentCommentId() {
			return parentCommentId;
		}




		public void setParentCommentId(Long parentCommentId) {
			this.parentCommentId = parentCommentId;
		}




		public String getComments() {
			return comments;
		}




		public void setComments(String comments) {
			this.comments = comments;
		}




		public User getSenderUser() {
			return senderUser;
		}




		public void setSenderUser(User senderUser) {
			this.senderUser = senderUser;
		}




		public User getReceiverUser() {
			return receiverUser;
		}




		public void setReceiverUser(User receiverUser) {
			this.receiverUser = receiverUser;
		}




		public UserPost getPost() {
			return post;
		}




		public void setPost(UserPost post) {
			this.post = post;
		}




		public UserPost getPostId() {
			return postId;
		}




		public void setPostId(UserPost postId) {
			this.postId = postId;
		}




		public String getCommenterEmail() {
			return commenterEmail;
		}




		public void setCommenterEmail(String commenterEmail) {
			this.commenterEmail = commenterEmail;
		}
		




	public UserComment(int id, Long parentCommentId, String comments, User senderUser, User receiverUser, UserPost post,
				UserPost postId, String commenterEmail) {
			super();
			this.id = id;
			this.parentCommentId = parentCommentId;
			this.comments = comments;
			this.senderUser = senderUser;
			this.receiverUser = receiverUser;
			this.post = post;
			this.postId = postId;
			this.commenterEmail = commenterEmail;
		}
	




	@Override
	public String toString() {
		return "Comment [id=" + id + ", parentCommentId=" + parentCommentId + ", comments=" + comments + ", senderUser="
				+ senderUser + ", receiverUser=" + receiverUser + ", post=" + post + ", postId=" + postId
				+ ", commenterEmail=" + commenterEmail + "]";
	}




	public UserComment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
