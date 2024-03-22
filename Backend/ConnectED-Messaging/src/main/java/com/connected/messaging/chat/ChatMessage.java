package com.connected.messaging.chat;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class ChatMessage {
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
    private String id = UUID.randomUUID().toString(); // Using UUID for generating unique IDs;
    private String chatId; // Changed type to String
    private String senderId;
    private String recipientId;
    private String content;
	private Date timestamp;
    
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", chatId=" + chatId + ", senderId=" + senderId + ", recipientId="
				+ recipientId + ", content=" + content + ", timestamp=" + timestamp + "]";
	}


}


/*
 * package com.alibou.websocket.chat;
 * 
 * import java.util.Date;
 * 
 * import org.springframework.data.annotation.Id;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Table;
 * 
 * @Entity
 * 
 * @Table public class ChatMessage {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; private
 * Long chatId; private Long senderId; private Long recipientId; private String
 * content; private Date timestamp;
 * 
 * @Override public String toString() { return "ChatMessage [id=" + id +
 * ", chatId=" + chatId + ", senderId=" + senderId + ", recipientId=" +
 * recipientId + ", content=" + content + ", timestamp=" + timestamp + "]"; }
 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
 * } public Long getChatId() { return chatId; } public void setChatId(Long
 * chatId) { this.chatId = chatId; } public Long getSenderId() { return
 * senderId; } public void setSenderId(Long senderId) { this.senderId =
 * senderId; } public Long getRecipientId() { return recipientId; } public void
 * setRecipientId(Long recipientId) { this.recipientId = recipientId; } public
 * String getContent() { return content; } public void setContent(String
 * content) { this.content = content; } public Date getTimestamp() { return
 * timestamp; } public void setTimestamp(Date timestamp) { this.timestamp =
 * timestamp; }
 * 
 * }
 */