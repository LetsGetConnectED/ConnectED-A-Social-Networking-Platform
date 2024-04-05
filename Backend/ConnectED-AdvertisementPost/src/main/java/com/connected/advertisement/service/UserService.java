package com.connected.advertisement.service;

import com.connected.advertisement.model.User;

public interface UserService {
	
	
	 User registerUser(User user);
	    User findUserByEmail(String email) throws Exception;
	    User updateUser(User user, String email) throws Exception;
	    void deleteUserByEmail(String email) throws Exception;


}
