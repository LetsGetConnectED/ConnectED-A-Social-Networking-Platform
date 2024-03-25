package com.ConnectED.Profile.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

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

import com.ConnectED.Profile.model.Advertiser;
import com.ConnectED.Profile.model.Profile;
import com.ConnectED.Profile.repository.AdvertiserRepository;
import com.ConnectED.Profile.service.AdvertiserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/advertisers")
public class AdvertiserController {
    @Autowired
    private AdvertiserService advertiserService;
   
    @GetMapping("/{email}")
    public ResponseEntity<Advertiser> getFullProfileByEmail(@PathVariable String email) {
        
        Advertiser profile =advertiserService.getByEmail(email);
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
    public ResponseEntity<Advertiser> createOrUpdateProfile(
        HttpServletRequest request,
        @RequestParam("image") MultipartFile file,
        @RequestParam("profile") String profileJson
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Advertiser profile = objectMapper.readValue(profileJson, Advertiser.class);
            byte[] bytes = file.getBytes();
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
            profile.setImage(imageBlob);
            Advertiser savedProfile = advertiserService.save(profile);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (IOException| SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteProfileByEmail(@PathVariable String email) {
    	advertiserService.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/{email}")
//    public ResponseEntity<Advertiser> getByEmail(@PathVariable String email) {
//       
//    }
//
    @PutMapping("api/{email}")
    public ResponseEntity<Advertiser> updateProfile(
            @PathVariable String email,
            @RequestParam("image") MultipartFile file,
            @RequestParam("profile") String profileJson) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
        
            Profile profile = objectMapper.readValue(profileJson, Profile.class);

            
            if (!file.isEmpty()) {
                //profile.setImage(file.getBytes()); // Assuming setImage method accepts byte[]
            }

         
            Advertiser updatedProfile = advertiserService.updateByEmail(email);

            if (updatedProfile != null) {
                return ResponseEntity.ok(updatedProfile);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

  
}