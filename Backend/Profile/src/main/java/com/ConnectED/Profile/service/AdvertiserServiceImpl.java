package com.connected.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.connected.profile.model.Advertiser;
import com.connected.profile.repository.AdvertiserRepository;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {

	
	@Autowired
    private AdvertiserRepository advertiserRepository;

    public Advertiser save(Advertiser advertiser) {
        return advertiserRepository.save(advertiser);
    }

    public Advertiser getByEmail(String email) {
        return advertiserRepository.findByEmail(email);
    }
   
  
    @Override
    public void deleteByEmail(String email) {
    	Advertiser profile = advertiserRepository.findByEmail(email);
        if (profile != null) {
        	advertiserRepository.delete(profile);
        }  else {
            throw new IllegalArgumentException("Email cannot be null"); 
        }
    }
}
