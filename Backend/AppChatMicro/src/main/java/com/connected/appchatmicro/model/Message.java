package com.connected.appchatmicro.model;

import javax.persistence.*;

import java.time.Instant;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String message;
    private Long receiverId;
    private Long senderId;

    // You may uncomment these associations if needed
//    @ManyToOne
//    @JoinColumn(name = "receiverId", referencedColumnName = "userId")
//    private User receiverUser;
//
//    @ManyToOne
//    @JoinColumn(name = "senderId", referencedColumnName = "userId")
//    private User senderUser;

    private Instant timeCreated = Instant.now();

    public Message() {
        // Default constructor
    }

    public Message(Long senderId, Long receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }

    // Getters and setters
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", message='" + message + '\'' +
                ", receiverId=" + receiverId +
                ", senderId=" + senderId +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
