package com.ConnectED.Profile.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

//import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ConnectED.Profile.model.Profile;
import com.ConnectED.Profile.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/User")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    
    
    @GetMapping("/{email}")
    public ResponseEntity<Profile> getFullProfileByEmail(@PathVariable String email) {
        Profile profile = profileService.getByEmail(email);
        if (profile != null) {
        	
            try {
                Blob imageBlob = profile.getImage();
                byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                profile.setImageBase64(base64Image);
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
    public ResponseEntity<Profile> createOrUpdateProfile(
        HttpServletRequest request,
        @RequestParam("image") MultipartFile file,
        @RequestParam("profile") String profileJson
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Profile profile = objectMapper.readValue(profileJson, Profile.class);
            byte[] bytes = file.getBytes();
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
            profile.setImage(imageBlob);
            Profile savedProfile = profileService.saveOrUpdate(profile);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (IOException| SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteProfileByEmail(@PathVariable String email) {
        profileService.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/update/{email}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable String email,
            @RequestParam("image") MultipartFile file,
            @RequestParam("profile") String profileJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
          
            byte[] bytes = file.getBytes();
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
            Profile updatedProfile = objectMapper.readValue(profileJson, Profile.class);
            Profile existingProfile = profileService.getByEmail(email);
            if (existingProfile == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
            existingProfile.setBio(updatedProfile.getBio());
            existingProfile.setCity(updatedProfile.getCity());
            existingProfile.setCountry(updatedProfile.getCountry());
            existingProfile.setEdu(updatedProfile.getEdu());
            existingProfile.setGender(updatedProfile.getGender());
            existingProfile.setMob(updatedProfile.getMob());
            existingProfile.setSkill(updatedProfile.getSkill());
            existingProfile.setOccupation(updatedProfile.getOccupation());
            existingProfile.setState(updatedProfile.getState());
            existingProfile.setUserName(updatedProfile.getUserName());
            existingProfile.setWork_exp(updatedProfile.getWork_exp());
            existingProfile.setImage(imageBlob);
            Profile savedProfile = profileService.saveOrUpdate(existingProfile);

            return new ResponseEntity<>(savedProfile, HttpStatus.OK);
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
    

