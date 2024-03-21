package com.ConnectED.Profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ConnectED.Profile.model.Profile;
import com.ConnectED.Profile.repository.ProfileRepository;

@Service
	public class ProfileServiceImpl implements ProfileService {

	    @Autowired
	    private ProfileRepository profileRepository;
	    @Override
	    public Profile getByEmail(String email) {
	        return profileRepository.findByEmail(email);
	    }
	    
	    @Override
	    public Profile saveOrUpdate(Profile profile) {
	        return profileRepository.save(profile);
	    }
	    
	    @Override
	    public void deleteByEmail(String email) {
	        Profile profile = profileRepository.findByEmail(email);
	        if (profile != null) {
	            profileRepository.delete(profile);
	        }
	    }
	}


