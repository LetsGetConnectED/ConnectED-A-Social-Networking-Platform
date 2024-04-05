package com.connected.profile.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.connected.profile.model.Advertiser;
import com.connected.profile.service.AdvertiserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
@RequestMapping("/advertiser")
public class AdvertiserController {
    @Autowired
    private AdvertiserService advertiserService;
   
    @GetMapping("/{email}")
    public ResponseEntity<Advertiser> getFullProfileByEmail(@PathVariable String email) {
        
        Advertiser profile =advertiserService.getByEmail(email);
        if (profile != null) {
        	
            try {
                Blob imageBlob = profile.getImage(); 
                if (imageBlob != null) {
              	byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
                  String base64Image = Base64.getEncoder().encodeToString(bytes);
                  profile.setImageBase64(base64Image);
              } else {
              	profile.setImageBase64(null);
              }
                return new ResponseEntity<>(profile, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
    @PostMapping("/save")
    public ResponseEntity<?> createOrUpdateProfile(
            HttpServletRequest request,
            @RequestParam(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "profile",required = false) String profileJson
    ) {
        if (profileJson == null) {
           
            return ResponseEntity.badRequest().body("Profile JSON is required.");
        }

        try {
        	ObjectMapper objectMapper = new ObjectMapper();
            Advertiser profile = objectMapper.readValue(profileJson, Advertiser.class);

            if (file != null) { 
                byte[] bytes = file.getBytes();
                Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
                profile.setImage(imageBlob);
            }

            Advertiser savedProfile = advertiserService.save(profile);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
 }
    
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteProfileByEmail(@PathVariable String email) {
    	advertiserService.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    
    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateProfile(
            @PathVariable String email,
            @RequestParam(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "profile", required = false) String profileJson) {

        if (profileJson == null) {
            return ResponseEntity.badRequest().body("Profile JSON is required.");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Advertiser updatedProfile = objectMapper.readValue(profileJson, Advertiser.class);

            Advertiser existingProfile = advertiserService.getByEmail(email);
            if (existingProfile == null) {
                return ResponseEntity.notFound().build();
            }

            if (file != null && !file.isEmpty()) { 
                byte[] bytes = file.getBytes();
                Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
                existingProfile.setImage(imageBlob);
            }
            
            if (file == null) { 
                
                existingProfile.setImage(null);
            }

          
            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
            existingProfile.setBio(updatedProfile.getBio());
            existingProfile.setCity(updatedProfile.getCity());
            existingProfile.setCountry(updatedProfile.getCountry());
            existingProfile.setMob(updatedProfile.getMob());
            existingProfile.setGender(updatedProfile.getGender());
            existingProfile.setCompanyName(updatedProfile.getCompanyName());
            existingProfile.setState(updatedProfile.getState());
            existingProfile.setUserName(updatedProfile.getUserName());
            Advertiser savedProfile = advertiserService.save(existingProfile);
            return new ResponseEntity<>(savedProfile, HttpStatus.OK);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request.");
        }
    }
  
}