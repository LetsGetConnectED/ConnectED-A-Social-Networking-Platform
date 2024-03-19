package com.connected.messaging.chatroom;

import java.util.UUID;

/*import org.springframework.data.annotation.Id;*/

import jakarta.persistence.Entity;
/*import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GenerationType;*/
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class ChatRoom {
	
	  @Id
		/*
		 * @GeneratedValue(strategy = GenerationType.IDENTITY)
		 */	 
	
	/* @Id */
	
	private String id = UUID.randomUUID().toString(); // Using UUID for generating unique IDs;

	private String chatId;
	private String senderId;
	private String recipientId;

	// Constructors
	public ChatRoom() {
	}

	// Builder method to start building a ChatRoom instance
	public static Builder builder() {
		return new Builder();
	}

	// Builder class for constructing ChatRoom instances
	public static class Builder {
		private String chatId;
		private String senderId;
		private String recipientId;

		private Builder() {
		}

		public Builder chatId(String chatId) {
			this.chatId = chatId;
			return this;
		}

		public Builder senderId(String senderId) {
			this.senderId = senderId;
			return this;
		}

		public Builder recipientId(String recipientId) {
			this.recipientId = recipientId;
			return this;
		}

		public ChatRoom build() {
			ChatRoom chatRoom = new ChatRoom();
			chatRoom.chatId = this.chatId;
			chatRoom.senderId = this.senderId;
			chatRoom.recipientId = this.recipientId;
			return chatRoom;
		}
	}

	// Getter and setter methods for all fields
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	// toString() method
	@Override
	public String toString() {
		return "ChatRoom{" + "id=" + id + ", chatId='" + chatId + '\'' + ", senderId='" + senderId + '\''
				+ ", recipientId='" + recipientId + '\'' + '}';
	}
}
