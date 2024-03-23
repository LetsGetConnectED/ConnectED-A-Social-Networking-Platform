package com.connected.appchatmicro.dto;

public class MessageRequest {

    private String receiver_username;
    private String sender_username;
    private String message;
	public String getReceiver_username() {
		return receiver_username;
	}
	@Override
	public String toString() {
		return "MessageRequest [receiver_username=" + receiver_username + ", sender_username=" + sender_username
				+ ", message=" + message + "]";
	}
	public void setReceiver_username(String receiver_username) {
		this.receiver_username = receiver_username;
	}
	public String getSender_username() {
		return sender_username;
	}
	public void setSender_username(String sender_username) {
		this.sender_username = sender_username;
	}
	public MessageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

