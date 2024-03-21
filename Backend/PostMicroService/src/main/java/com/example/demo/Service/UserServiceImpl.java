package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;


	@Override
	public User registeredUser(User user) {
		User newUser = new User();
		
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		
		newUser.setId(user.getId());
		
		User savedUser = userRepository.save(newUser);
	
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		throw new Exception("users not exist with userid"+userId);
	}
	@Override
	public User updateUser(User user,Integer userId) throws Exception {
		Optional<User> user1 = userRepository.findById(userId);
		if(user1.isEmpty()) {
			throw new Exception("user not exit with id"+userId);
		}
		User oldUser=user1.get();
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		
		User updatedUser=userRepository.save(oldUser);
		return updatedUser;
		
	}
@Override
	
	public User getUserWithPosts(int userId) {
		 return userRepository.findById(userId).orElse(null);
	}



}
