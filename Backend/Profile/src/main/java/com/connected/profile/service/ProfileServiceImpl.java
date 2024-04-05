package com.connected.profile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.connected.profile.exception.ResourceNotFoundException;
import com.connected.profile.model.Profile;
import com.connected.profile.repository.ProfileRepository;

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
    public void deleteByEmail(String email) {
        Profile profile = profileRepository.findByEmail(email);
        if (profile != null) {
            profileRepository.delete(profile);
        }  else {
            throw new IllegalArgumentException("Email cannot be null"); 
        }
    }
}