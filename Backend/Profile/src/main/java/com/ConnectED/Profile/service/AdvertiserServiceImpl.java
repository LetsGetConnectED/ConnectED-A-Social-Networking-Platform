package com.ConnectED.Profile.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ConnectED.Profile.model.Advertiser;
import com.ConnectED.Profile.model.Profile;
import com.ConnectED.Profile.repository.AdvertiserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	//@Override
    public Advertiser updateByEmail(String email) {
        Advertiser advertiser = advertiserRepository.findByEmail(email);
        if (advertiser != null) {
            advertiser.setImage(getByEmail(email).getImage());
            advertiser.setFirstName(getByEmail(email).getFirstName());
            advertiser.setLastName(getByEmail(email).getLastName());
            return advertiserRepository.save(advertiser);
        }
        return null; 
    }

   
    
    
  
    @Override
    public void deleteByEmail(String email) {
    	Advertiser profile = advertiserRepository.findByEmail(email);
        if (profile != null) {
        	advertiserRepository.delete(profile);
        }
    }


}
