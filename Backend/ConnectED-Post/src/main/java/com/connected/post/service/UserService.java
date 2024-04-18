package com.connected.post.service;

import com.connected.post.model.User;

public interface UserService {
	
	
	 User registerUser(User user);
	    User findUserByEmail(String email) throws Exception;
	    User updateUser(User user, String email) throws Exception;
	    void deleteUserByEmail(String email) throws Exception;


}
