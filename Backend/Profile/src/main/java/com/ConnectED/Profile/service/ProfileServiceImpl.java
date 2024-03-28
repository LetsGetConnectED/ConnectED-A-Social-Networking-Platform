package com.ConnectED.Profile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ConnectED.Profile.exception.DeletedException;
import com.ConnectED.Profile.exception.ResourceNotFoundException;
import com.ConnectED.Profile.model.Profile;
import com.ConnectED.Profile.repository.ProfileRepository;


@Service
	public class ProfileServiceImpl implements ProfileService {

	    @Autowired
	    private ProfileRepository profileRepository;
	    
	    @Override
	    public Profile getByEmail(String email) {
	        Optional<Profile> userOptional = Optional.ofNullable(profileRepository.findByEmail(email));
	        return userOptional.orElseThrow(ResourceNotFoundException::new);
	    }
	    
	    @Override
	    public Profile saveOrUpdate(Profile profile) {
	        return profileRepository.save(profile);
	    }
	    
	    @Override
	    public void deleteByEmail(String email) throws DeletedException {
	        Profile profile = profileRepository.findByEmail(email);
	        if (profile != null) {
	            profileRepository.delete(profile);
	            throw new DeletedException();
	        }  else {
	            throw new IllegalArgumentException("Email cannot be null"); 
	        }
	    }
	}


