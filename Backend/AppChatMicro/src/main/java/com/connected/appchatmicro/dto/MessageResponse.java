package com.connected.appchatmicro.dto;


import java.time.Instant;

public class MessageResponse {

    public MessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MessageResponse [messageId=" + messageId + ", senderUsername=" + senderUsername + ", receiverUsername="
				+ receiverUsername + ", message=" + message + ", receiverId=" + receiverId + ", senderId=" + senderId
				+ ", timeCreated=" + timeCreated + ", duration=" + duration + ", stringTimeCreated=" + stringTimeCreated
				+ "]";
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getSenderUsername() {
		return senderUsername;
	}
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	public String getReceiverUsername() {
		return receiverUsername;
	}
	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Instant getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Instant timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStringTimeCreated() {
		return stringTimeCreated;
	}
	public void setStringTimeCreated(String stringTimeCreated) {
		this.stringTimeCreated = stringTimeCreated;
	}
	private Long messageId;
    private String senderUsername;
    private String receiverUsername;
    private String message;
    private Long receiverId;
    private Long senderId;
    private Instant timeCreated;
    private String duration;
    private String stringTimeCreated;
}

