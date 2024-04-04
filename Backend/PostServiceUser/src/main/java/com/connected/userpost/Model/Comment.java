


package com.connected.userpost.Model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")

public class Comment {

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

//    @ManyToOne
//    @JoinColumn(name = "post_id")
    @ManyToOne
    @JoinColumn(name = "post_date", referencedColumnName = "date") // Referencing Post by date
    private Post post;

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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(int id, String comments, User senderUser, User receiverUser, Post post) {
		super();
		this.id = id;
		this.comments = comments;
		this.senderUser = senderUser;
		this.receiverUser = receiverUser;
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comments=" + comments + ", senderUser=" + senderUser + ", receiverUser="
				+ receiverUser + ", post=" + post + "]";
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
   
    

	

	



	

	
}
