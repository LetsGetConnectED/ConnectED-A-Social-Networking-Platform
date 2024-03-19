package com.connected.messaging.chat;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class ChatNotification {
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
    private String id = UUID.randomUUID().toString(); // Using UUID for generating unique IDs; // Change to String
    private String senderId; // Change to String
    private String recipientId; // Change to String
    private String content;

    // Constructors, getter/setter methods, and toString()...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ChatNotification{" +
                "id='" + id + '\'' +
                ", senderId='" + senderId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public ChatNotification() {
    }

    public ChatNotification(String id, String senderId, String recipientId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }
}



/*
 * package com.alibou.websocket.chat;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.persistence.Table;
 * 
 * @Entity
 * 
 * @Table public class ChatNotification {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private String id;
 * private String senderId;
 * 
 * @Override public String toString() { return "ChatNotification [id=" + id +
 * ", senderId=" + senderId + ", recipientId=" + recipientId + ", content=" +
 * content + "]"; } public String getId() { return id; } public
 * ChatNotification(String id, String senderId, String recipientId, String
 * content) { super(); this.id = id; this.senderId = senderId; this.recipientId
 * = recipientId; this.content = content; } public String getSenderId() { return
 * senderId; } public void setSenderId(String senderId) { this.senderId =
 * senderId; } public String getRecipientId() { return recipientId; } public
 * void setRecipientId(String recipientId) { this.recipientId = recipientId; }
 * public String getContent() { return content; } public void setContent(String
 * content) { this.content = content; } private String recipientId; private
 * String content; }
 */
