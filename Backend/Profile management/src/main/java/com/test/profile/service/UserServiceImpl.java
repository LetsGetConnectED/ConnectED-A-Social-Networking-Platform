package com.test.profile.service;

import java.util.Optional;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.profile.entity.User;
import com.test.profile.exception.DeletedException;
import com.test.profile.exception.ResourceNotFoundException;
import com.test.profile.repository.userRepository;

import jakarta.transaction.Transactional;
@DynamicUpdate
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private userRepository userRepository;
	
	
	@Override
	public User saveUser(User user) {
		 return userRepository.save(user);
	}

	@Override
	public User getUser(String email) {
		 Optional<User> userOptional = userRepository.findByEmail(email);
	        return userOptional.orElseThrow(() -> new ResourceNotFoundException() );
	}


	 public User putUser(String email, User newUser) throws ResourceNotFoundException {
	        Optional<User> userOptional = userRepository.findByEmail(email);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            
	            // Update existing user properties
	            user.setFirstName(newUser.getFirstName());
	            user.setLastName(newUser.getLastName());
	            user.setImg_Url(newUser.getImg_Url());
	            user.setGender(newUser.getGender());
	            user.setBio(newUser.getBio());
	            user.setEdu(newUser.getEdu());
	            user.setSkill(newUser.getSkill());
	            user.setWork_exp(newUser.getWork_exp());
	            user.setCity(newUser.getCity());
	            user.setMob(newUser.getMob());
	            user.setState(newUser.getState());
	            user.setCountry(newUser.getCountry());

	            // Save the updated user
	            return userRepository.save(user);
	        } else {
	            throw new ResourceNotFoundException("Given id not found: " );
	        }
	    }
   			
	
	@Override
//	public void deleteUser(String email) throws DeletedException {
//		if(email != null) {
//		 userRepository.deleteByEmail(email);
//		System.out.println("User deleted");
//		   throw new DeletedException();
//	        }
//		}
	

	 @Transactional(rollbackOn = DeletedException.class) // Ensure transactional behavior and rollback for DeletedException
    public void deleteUser(String email) throws DeletedException {
        if (email != null) {
            userRepository.deleteByEmail(email);
            System.out.println("User deleted");
            throw new DeletedException();
        } else {
            throw new IllegalArgumentException("Email cannot be null"); // Throw exception for null email
        }
    }
	
}