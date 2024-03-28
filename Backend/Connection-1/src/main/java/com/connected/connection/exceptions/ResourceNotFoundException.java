package com.connected.connection.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1179444829165953034L;

	public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: %s", resource, field, value));
    }
}
