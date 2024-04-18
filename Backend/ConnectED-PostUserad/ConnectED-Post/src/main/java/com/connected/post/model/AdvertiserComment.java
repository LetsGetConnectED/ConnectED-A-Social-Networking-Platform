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

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "receiver_email", referencedColumnName = "email")
    private Advertiser receiverUser;

    @ManyToOne
    @JoinColumn(name = "sender_email", referencedColumnName = "email")
    private User senderUser;

    @Column(name = "post_date")  // Store post date directly in AdvertiserComment
    private LocalDate postDate;
    
   // @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    
    private AdvertisementPost post;

    @Column
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

	

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	public String getCommenterEmail() {
		return commenterEmail;
	}

	public void setCommenterEmail(String commenterEmail) {
		this.commenterEmail = commenterEmail;
	}
	

	

	
	

	public AdvertisementPost getPost() {
		return post;
	}

	public void setPost(AdvertisementPost post) {
		this.post = post;
	}

	

	public AdvertiserComment(Long id, Long parentCommentId, String comment, Advertiser receiverUser, User senderUser,
			LocalDate postDate, AdvertisementPost post, String commenterEmail) {
		super();
		this.id = id;
		this.parentCommentId = parentCommentId;
		this.comment = comment;
		this.receiverUser = receiverUser;
		this.senderUser = senderUser;
		this.postDate = postDate;
		this.post = post;
		this.commenterEmail = commenterEmail;
	}

	public AdvertiserComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AdvertiserComment [id=" + id + ", parentCommentId=" + parentCommentId + ", comment=" + comment
				+ ", receiverUser=" + receiverUser + ", senderUser=" + senderUser + ", postDate=" + postDate + ", post="
				+ post + ", commenterEmail=" + commenterEmail + "]";
	}


	

	
}


//package com.connected.advertisement.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//
//@Embeddable
//public class Comment {
//
//	private String comment;
//
//	@ManyToOne
//	@JoinColumn(name = "receiver_email", referencedColumnName = "email")
//	private Advertiser receiverUser;
//
//	@ManyToOne
//	@JoinColumn(name = "sender_email", referencedColumnName = "email") // Changed sender_email to user_email
//	private User senderUser; // Changed to senderUser
//
//	@ManyToOne
//	@JoinColumn(name = "post_date", referencedColumnName = "postDate")
//	private AdvertisementPost post;
//
//	// Add commenterEmail to store the commenter's email
//	@NotBlank
//	@Email
//	@Column(nullable = false)
//	private String commenterEmail;
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	public Advertiser getReceiverUser() {
//		return receiverUser;
//	}
//
//	public void setReceiverUser(Advertiser receiverUser) {
//		this.receiverUser = receiverUser;
//	}
//
//	public User getSenderUser() {
//		return senderUser;
//	}
//
//	public void setSenderUser(User senderUser) {
//		this.senderUser = senderUser;
//	}
//
//	public AdvertisementPost getPost() {
//		return post;
//	}
//
//	public void setPost(AdvertisementPost post) {
//		this.post = post;
//	}
//
//	public String getCommenterEmail() {
//		return commenterEmail;
//	}
//
//	public void setCommenterEmail(String commenterEmail) {
//		this.commenterEmail = commenterEmail;
//	}
//
//	public Comment(String comment, Advertiser receiverUser, User senderUser, AdvertisementPost post,
//			@NotBlank @Email String commenterEmail) {
//		super();
//		this.comment = comment;
//		this.receiverUser = receiverUser;
//		this.senderUser = senderUser;
//		this.post = post;
//		this.commenterEmail = commenterEmail;
//	}
//
//	@Override
//	public String toString() {
//		return "Comment [comment=" + comment + ", receiverUser=" + receiverUser + ", senderUser=" + senderUser
//				+ ", post=" + post + ", commenterEmail=" + commenterEmail + "]";
//	}
//
//	public Comment() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//}
