package com.test.profile.service;

import java.util.Optional;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.profile.entity.User;
import com.test.profile.exception.ResourceNotFoundException;
import com.test.profile.repository.userRepository;
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
	public User getUser(Long u_id) {
		 Optional<User> userOptional = userRepository.findById(u_id);
	        return userOptional.orElse(null);
	}
//   @Override
//	public User putUser(Long u_id) throws ResourceNotFoundException {
//		Optional<User> userOptional = userRepository.findById(u_id);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return userRepository.save(user);
//        }  
//           throw new ResourceNotFoundException("Given id not found: ");
//        
//	}

	 public User putUser(Long u_id, User newUser) throws ResourceNotFoundException {
	        Optional<User> userOptional = userRepository.findById(u_id);
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
	            throw new ResourceNotFoundException("Given id not found: " + u_id);
	        }
	    }
   			
	
	@Override
	public void deleteUser(Long u_id) {

		userRepository.deleteById(u_id);
    }
}