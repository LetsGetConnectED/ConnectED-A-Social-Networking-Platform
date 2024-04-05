package com.connected.advertisement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Comment {

	 private String comment;

	    @ManyToOne
	    @JoinColumn(name = "receiver_email", referencedColumnName = "email")
	    private Advertiser receiverUser;

	    @ManyToOne
	    @JoinColumn(name = "user_email", referencedColumnName = "email") // Changed sender_email to user_email
	    private User senderUser; // Changed to senderUser

	   
	    
	    @ManyToOne
	    @JoinColumn(name = "post_date", referencedColumnName = "postDate")
	    private AdvertisementPost post;

	    // Add commenterEmail to store the commenter's email
	    @NotBlank
	    @Email
	    @Column(nullable = false)
	    private String commenterEmail;

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public Advertiser getReceiverUser() {
			return receiverUser;
		}

		public void setReceiverUser(Advertiser receiverUser) {
			this.receiverUser = receiverUser;
		}

		public User getSenderUser() {
			return senderUser;
		}

		public void setSenderUser(User senderUser) {
			this.senderUser = senderUser;
		}

		
		public AdvertisementPost getPost() {
			return post;
		}

		public void setPost(AdvertisementPost post) {
			this.post = post;
		}

		public String getCommenterEmail() {
			return commenterEmail;
		}

		public void setCommenterEmail(String commenterEmail) {
			this.commenterEmail = commenterEmail;
		}

		

		

		public Comment(String comment, Advertiser receiverUser, User senderUser, AdvertisementPost post,
				@NotBlank @Email String commenterEmail) {
			super();
			this.comment = comment;
			this.receiverUser = receiverUser;
			this.senderUser = senderUser;
			this.post = post;
			this.commenterEmail = commenterEmail;
		}
		

		@Override
		public String toString() {
			return "Comment [comment=" + comment + ", receiverUser=" + receiverUser + ", senderUser=" + senderUser
					+ ", post=" + post + ", commenterEmail=" + commenterEmail + "]";
		}

		public Comment() {
			super();
			// TODO Auto-generated constructor stub
		}

  

	

    
    
	
	

    
}
