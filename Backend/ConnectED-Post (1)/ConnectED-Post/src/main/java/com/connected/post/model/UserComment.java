
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

	   

	    private String comments;

	    @ManyToOne
	    @JoinColumn(name = "sender_email", referencedColumnName = "email") // Changed to sender_email
	    private User senderUser;

	    @ManyToOne
	    @JoinColumn(name = "receiver_email", referencedColumnName = "email") // Changed to receiver_email
	    private User receiverUser;

	  

	    @ManyToOne
	    @JoinColumn(name = "post_id", referencedColumnName = "id")
	    private UserPost postId;



		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
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



		public UserPost getPostId() {
			return postId;
		}



		public void setPostId(UserPost postId) {
			this.postId = postId;
		}



		public UserComment(int id, String comments, User senderUser, User receiverUser, UserPost postId) {
			super();
			this.id = id;
			this.comments = comments;
			this.senderUser = senderUser;
			this.receiverUser = receiverUser;
			this.postId = postId;
		}



		@Override
		public String toString() {
			return "UserComment [id=" + id + ", comments=" + comments + ", senderUser=" + senderUser + ", receiverUser="
					+ receiverUser + ", postId=" + postId + "]";
		}



		public UserComment() {
			super();
			// TODO Auto-generated constructor stub
		}

	   
	    
	    


	

}
