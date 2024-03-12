package com.connected.appchatmicro.dto;

import java.time.Instant;
public class AuthenticationResponse {
    public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AuthenticationResponse [authenticationToken=" + authenticationToken + ", refreshToken=" + refreshToken
				+ ", expiresAt=" + expiresAt + ", username=" + username + "]";
	}
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Instant getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;
}