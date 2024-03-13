package com.connected.appchatmicro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity

public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Instant createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public RefreshToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RefreshToken [id=" + id + ", token=" + token + ", createdDate=" + createdDate + "]";
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
}