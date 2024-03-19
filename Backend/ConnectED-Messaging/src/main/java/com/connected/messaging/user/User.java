package com.connected.messaging.user;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
	/*
	 * @Id
	 * 
	 * @GeneratedValue (strategy = GenerationType.IDENTITY)
	 */
	
	@Id
    private String nickName = UUID.randomUUID().toString(); // Using UUID for generating unique IDs;
    private String fullName;
    public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "User [nickName=" + nickName + ", fullName=" + fullName + ", status=" + status + "]";
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	private Status status;
}
