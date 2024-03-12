package com.connected.appchatmicro.dto;


import javax.validation.constraints.NotBlank;

public class RefreshTokenRequest {
    public RefreshTokenRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RefreshTokenRequest [refreshToken=" + refreshToken + ", username=" + username + "]";
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NotBlank
    private String refreshToken;
    private String username;
}
