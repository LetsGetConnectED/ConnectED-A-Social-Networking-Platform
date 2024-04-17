package com.connected.post.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AdvertiserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String comment;

    @ManyToOne
    @JoinColumn(name = "receiver_email", referencedColumnName = "email")
    private Advertiser receiverUser;

    @ManyToOne
    @JoinColumn(name = "sender_email", referencedColumnName = "email")
    private User senderUser;


    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    
    private AdvertisementPost post;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

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
	

	

	

	

	

	

	@Override
	public String toString() {
		return "AdvertiserComment [id=" + id + ", comment=" + comment + ", receiverUser=" + receiverUser
				+ ", senderUser=" + senderUser + ", post=" + post + "]";
	}

	public AdvertiserComment(Long id, String comment, Advertiser receiverUser, User senderUser,
			AdvertisementPost post) {
		super();
		this.id = id;
		this.comment = comment;
		this.receiverUser = receiverUser;
		this.senderUser = senderUser;
		this.post = post;
	}

	public AdvertiserComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	


	

	
}



