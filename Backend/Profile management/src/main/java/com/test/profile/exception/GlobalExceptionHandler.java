package com.test.profile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.profile.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message =ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false,HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DeletedException.class)
	public ResponseEntity<ApiResponse> DeletedException(DeletedException ex){
		String message =ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,true,HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.MOVED_PERMANENTLY);
	}

}
