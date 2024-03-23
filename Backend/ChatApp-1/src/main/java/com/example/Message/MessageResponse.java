package com.example.Message;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
@Entity
public class MessageResponse {
    private Long messageId;
    private String senderId;
    private String recipientId;
    private String content;
    private LocalDateTime createdAt;


    public MessageResponse(Long messageId, String senderId, String recipientId, String content) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

