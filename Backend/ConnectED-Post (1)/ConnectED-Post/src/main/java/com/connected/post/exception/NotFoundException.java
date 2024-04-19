package com.connected.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8532274954104890825L;

    public NotFoundException(String message) {
        super(message);
    }

    public static class PostNotFoundException extends NotFoundException {
        public PostNotFoundException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends NotFoundException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

}


//package com.connected.post.exception;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ResponseStatus(HttpStatus.NOT_FOUND)
//public class NotFoundException extends RuntimeException {
//
//    
//	private static final long serialVersionUID = -8532274954104890825L;
//
//	public NotFoundException(String message) {
//        super(message);
//    }
//}

