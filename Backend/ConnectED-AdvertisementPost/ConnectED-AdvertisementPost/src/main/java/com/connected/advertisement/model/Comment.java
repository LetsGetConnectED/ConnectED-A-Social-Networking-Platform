package com.connected.advertisement.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Comment {

    private String comment;
    @ManyToOne
    @JoinColumn(name = "receiver_email", referencedColumnName = "email")
    private Advertiser receiverUser;

    @ManyToOne
    @JoinColumn(name = "sender_email", referencedColumnName = "email")
    private Advertiser senderUser;

    @ManyToOne
    @JoinColumn(name = "post_date", referencedColumnName = "postDate")
    private AdvertisementPost post;

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

	public Advertiser getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(Advertiser senderUser) {
		this.senderUser = senderUser;
	}

	public AdvertisementPost getPost() {
		return post;
	}

	public void setPost(AdvertisementPost post) {
		this.post = post;
	}

	public Comment(String comment, Advertiser receiverUser, Advertiser senderUser, AdvertisementPost post) {
		super();
		this.comment = comment;
		this.receiverUser = receiverUser;
		this.senderUser = senderUser;
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", receiverUser=" + receiverUser + ", senderUser=" + senderUser
				+ ", post=" + post + "]";
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    
	
	

    
}
