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
            // Update other fields similarly
            // advertiser.setUserName(updatedAdvertiser.getUserName());
            // advertiser.setFirstName(updatedAdvertiser.getFirstName());
            // ...
            return advertiserRepository.save(advertiser);
        }
        return null; 
    }

    
//    public ResponseEntity<Profile> updateProfile(
//            @PathVariable String email,
//            @RequestParam("image") MultipartFile file,
//            @RequestParam("profile") String profileJson) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//          
//            byte[] bytes = file.getBytes();
//            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
//            Profile updatedProfile = objectMapper.readValue(profileJson, Profile.class);
//            Profile existingProfile = profileService.getByEmail(email);
//            if (existingProfile == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            existingProfile.setFirstName(updatedProfile.getFirstName());
//            existingProfile.setLastName(updatedProfile.getLastName());
//            existingProfile.setBio(updatedProfile.getBio());
//            existingProfile.setCity(updatedProfile.getCity());
//            existingProfile.setCountry(updatedProfile.getCountry());
//            existingProfile.setEdu(updatedProfile.getEdu());
//            existingProfile.setGender(updatedProfile.getGender());
//            existingProfile.setMob(updatedProfile.getMob());
//            existingProfile.setSkill(updatedProfile.getSkill());
//            existingProfile.setOccupation(updatedProfile.getOccupation());
//            existingProfile.setState(updatedProfile.getState());
//            existingProfile.setUserName(updatedProfile.getUserName());
//            existingProfile.setWork_exp(updatedProfile.getWork_exp());
//            existingProfile.setImage(imageBlob);
//            Profile savedProfile = profileService.saveOrUpdate(existingProfile);
//
//            return new ResponseEntity<>(savedProfile, HttpStatus.OK);
//            
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    
  
    @Override
    public void deleteByEmail(String email) {
    	Advertiser profile = advertiserRepository.findByEmail(email);
        if (profile != null) {
        	advertiserRepository.delete(profile);
        }
    }

//	@Override
//	public Advertiser UpdateByEmail(Advertiser email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
