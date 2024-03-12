package com.connected.appchatmicro.exceptions;
public class SpringChatException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1395174347002922737L;

	public SpringChatException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringChatException(String exMessage) {
        super(exMessage);
    }
}