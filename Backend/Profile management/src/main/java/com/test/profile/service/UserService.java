package com.test.profile.service;


import com.test.profile.entity.User;

public interface UserService {
	
	User saveUser(User user);
	//get single user of given userId
	User getUser(String email);
	
	//User putUser(Long u_id);
	User putUser(String email,User user);
	
	void deleteUser(String  email);
	

}
