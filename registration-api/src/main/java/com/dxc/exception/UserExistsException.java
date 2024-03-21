package com.dxc.exception;

public class UserExistsException extends Exception {
	private static final long serialVersionUID = -8275453305711703950L;
	String msg;

	public UserExistsException() {
		super();
	}

	public UserExistsException(String msg) {
		super(msg);
		
	}
}
