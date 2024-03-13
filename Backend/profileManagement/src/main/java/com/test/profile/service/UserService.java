package com.test.profile.service;


import com.test.profile.entity.User;

public interface UserService {
	
	User saveUser(User user);
	//get single user of given userId
	User getUser(Long u_id);
	
	User putUser(Long u_id);
	
	void deleteUser(Long u_id);
	
	

}
