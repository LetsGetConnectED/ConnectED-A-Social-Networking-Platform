package com.dxc.exception;

public class IncorrectPinException extends Exception {
	private static final long serialVersionUID = 2752052006348146061L;
	String msg;

	public IncorrectPinException() {
		super();
	}

	public IncorrectPinException(String msg) {
		super(msg);

	}
}
