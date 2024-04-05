package com.connected.profile.payload;

import org.springframework.http.HttpStatus;


public class ApiResponse {

	public ApiResponse(String message, boolean success , HttpStatus status) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
	}
	private String message;
	private boolean success;
	private HttpStatus status;
}