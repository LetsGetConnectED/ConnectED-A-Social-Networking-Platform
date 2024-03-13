package com.dxc.exception;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = -2069010704600357632L;
	String msg;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String msg) {
		super(msg);
		
	}
	
}
