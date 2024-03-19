package com.example.demo.Service;

import com.example.demo.Model.User;

public interface UserService {
	public User registeredUser(User user);
	public User findUserById(Integer userId) throws Exception;
	public User updateUser(User user,Integer userId) throws Exception;
	 User getUserWithPosts(int userId);

}
