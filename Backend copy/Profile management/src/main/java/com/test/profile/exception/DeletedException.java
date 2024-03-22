package com.test.profile.exception;

public class DeletedException extends RuntimeException{

	public DeletedException() {
		super("User data deleted successfully!");
	
	}
	public DeletedException(String message) {
		super("message");
		
	}
	
	

}
