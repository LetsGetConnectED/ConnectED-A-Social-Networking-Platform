package com.connected.userpost.Service;

import com.connected.userpost.Model.User;

public interface UserService {
	
	
	 User registerUser(User user);
	    User findUserByEmail(String email) throws Exception;
	    User updateUser(User user, String email) throws Exception;
	    void deleteUserByEmail(String email) throws Exception;


}
