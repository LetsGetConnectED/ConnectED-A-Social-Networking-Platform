package com.connected.connection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    private RequestStatus status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	public FriendRequest(Long id, User sender, User receiver, RequestStatus status) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.status = status;
	}
	@Override
	public String toString() {
		return "FriendRequest [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", status=" + status + "]";
	}
	public FriendRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}
