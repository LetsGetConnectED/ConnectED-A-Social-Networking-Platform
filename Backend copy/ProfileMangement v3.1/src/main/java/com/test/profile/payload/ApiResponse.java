package com.test.profile.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Builder
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
