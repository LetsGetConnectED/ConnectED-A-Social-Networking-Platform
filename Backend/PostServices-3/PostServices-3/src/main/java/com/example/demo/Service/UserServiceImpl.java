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
	    public User registerUser(User user) {
	        return userRepository.save(user);
	    }

	    @Override
	    public User findUserByEmail(String email) throws Exception {
	        Optional<User> user = userRepository.findByEmail(email);
	        if (user.isPresent()) {
	            return user.get();
	        }
	        throw new Exception("User not found with email " + email);
	    }

	    @Override
	    public User updateUser(User user, String email) throws Exception {
	        User existingUser = findUserByEmail(email);
	        
	       
	        if (user.getFirstName() != null) {
	            existingUser.setFirstName(user.getFirstName());
	        }
	        if (user.getLastName() != null) {
	            existingUser.setLastName(user.getLastName());
	        }
	        
	        return userRepository.save(existingUser);
	    }

	    @Override
	    public void deleteUserByEmail(String email) throws Exception {
	        User user = findUserByEmail(email);
	        userRepository.delete(user);
	    }
}






