package com.test.profile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.profile.entity.User;
import com.test.profile.exception.ResourceNotFoundException;
import com.test.profile.repository.userRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private userRepository userRepository;
	@Override
	public User saveUser(User user) {
		 return userRepository.save(user);
	}

	@Override
	public User getUser(Long u_id) {
		 Optional<User> userOptional = userRepository.findById(u_id);
	        return userOptional.orElse(null);
	}

	@Override
	public User putUser(Long u_id) throws ResourceNotFoundException {
		Optional<User> userOptional = userRepository.findById(u_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userRepository.save(user);
        }  
           throw new ResourceNotFoundException("Given id not found: ");
        
	}

	@Override
	public void deleteUser(Long u_id) {

		userRepository.deleteById(u_id);
    }
}