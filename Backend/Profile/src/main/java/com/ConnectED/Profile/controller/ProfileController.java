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
//import com.test.profile.entity.Image;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    
    
    @GetMapping("/profiles/{email}")
    public ResponseEntity<Profile> getProfileByEmail(@PathVariable String email) {
        Profile profile = profileService.getByEmail(email);
        if (profile != null) {
            try {
                // Optionally, you can remove the Blob data from the profile object to reduce response size
                profile.setImage(null);
                return new ResponseEntity<>(profile, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profiles/{email}/image")
    public ResponseEntity<String> getProfileImageByEmail(@PathVariable String email) {
        Profile profile = profileService.getByEmail(email);
        if (profile != null) {
            try {
                Blob imageBlob = profile.getImage();
                byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                return new ResponseEntity<>(base64Image, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/api")
    public ResponseEntity<Profile> createOrUpdateProfile(
        HttpServletRequest request,
        @RequestParam("image") MultipartFile file,
        @RequestParam("profile") String profileJson
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Profile profile = objectMapper.readValue(profileJson, Profile.class);

            // Convert MultipartFile to byte array
            byte[] bytes = file.getBytes();

            // Convert byte array to Blob
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);

            // Set Blob to the profile's image field
            profile.setImage(imageBlob);

            Profile savedProfile = profileService.saveOrUpdate(profile);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (IOException| SQLException e) {
        	//catch (IOException | SerialException | SQLException e)
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteProfileByEmail(@PathVariable String email) {
        profileService.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/api/{email}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable String email,
            @RequestParam("image") MultipartFile file,
            @RequestParam("profile") String profileJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert MultipartFile to byte array
            byte[] bytes = file.getBytes();

            // Convert byte array to Blob
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);

            // Deserialize profile JSON to Profile object
            Profile updatedProfile = objectMapper.readValue(profileJson, Profile.class);

            // Search for existing profile by email
            Profile existingProfile = profileService.getByEmail(email);
            if (existingProfile == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Update profile data
            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
           // existingProfile.setAge(updatedProfile.getAge());
            // Update other fields similarly...

            // Update image
            existingProfile.setImage(imageBlob);

            // Save or update the profile
            Profile savedProfile = profileService.saveOrUpdate(existingProfile);

            return new ResponseEntity<>(savedProfile, HttpStatus.OK);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
    

