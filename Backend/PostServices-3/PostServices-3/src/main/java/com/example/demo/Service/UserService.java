package com.example.demo.Service;

import com.example.demo.Model.User;

public interface UserService {
	
	
	 User registerUser(User user);
	    User findUserByEmail(String email) throws Exception;
	    User updateUser(User user, String email) throws Exception;
	    void deleteUserByEmail(String email) throws Exception;


}
