package com.test.profile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.profile.entity.User;
import com.test.profile.repository.userRepository;
import com.test.profile.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private userRepository userRepository;
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		 return userRepository.save(user);
	}

	@Override
	public User getUser(Long u_id) {
		// TODO Auto-generated method stub
		 Optional<User> userOptional = userRepository.findById(u_id);
	        return userOptional.orElse(null);
	}

	@Override
	public User putUser(Long u_id) {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findById(u_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Modify user properties as needed
            return userRepository.save(user);
        } else {
            return null; // or throw an exception, depending on your requirements
        }
	}

	@Override
	public void DeleteUser(Long u_id) {

		userRepository.deleteById(u_id);
    }
}
