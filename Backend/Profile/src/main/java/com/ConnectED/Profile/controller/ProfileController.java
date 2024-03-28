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
import com.ConnectED.Profile.model.Recruiter;
import com.ConnectED.Profile.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    
    
    @GetMapping("/{email}")
    public ResponseEntity<Profile> getFullProfileByEmail(@PathVariable String email) {
        Profile profile = profileService.getByEmail(email);
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
            Profile profile = objectMapper.readValue(profileJson, Profile.class);

            if (file != null) { 
                byte[] bytes = file.getBytes();
                Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
                profile.setImage(imageBlob);
            }

            Profile savedProfile = profileService.saveOrUpdate(profile);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (IOException | SQLException e) {
        	e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.valueOf("email already present"));
        }
 }

    
   
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteProfileByEmail(@PathVariable String email) {
        profileService.deleteByEmail(email);
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
        Profile updatedProfile = objectMapper.readValue(profileJson, Profile.class);

        Profile existingProfile = profileService.getByEmail(email);
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
        existingProfile.setEdu(updatedProfile.getEdu());
        existingProfile.setGender(updatedProfile.getGender());
        existingProfile.setMob(updatedProfile.getMob());
        existingProfile.setSkill(updatedProfile.getSkill());
        existingProfile.setOccupation(updatedProfile.getOccupation());
        existingProfile.setState(updatedProfile.getState());
        existingProfile.setUserName(updatedProfile.getUserName());
        existingProfile.setWork_exp(updatedProfile.getWork_exp());

        Profile savedProfile = profileService.saveOrUpdate(existingProfile);
        return ResponseEntity.ok(savedProfile);
    } catch (IOException | SQLException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request.");
    }
}
}

