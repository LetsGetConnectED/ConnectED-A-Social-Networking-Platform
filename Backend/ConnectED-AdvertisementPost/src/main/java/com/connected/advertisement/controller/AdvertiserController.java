package com.connected.advertisement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connected.advertisement.model.Advertiser;
import com.connected.advertisement.service.AdvertiserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/advertisers")
public class AdvertiserController {

    @Autowired
    private AdvertiserService advertiserService;

    @PostMapping      //Poojitha
    public ResponseEntity<Advertiser> createAdvertiser( @RequestBody Advertiser advertiser) {
        return ResponseEntity.ok(advertiserService.createAdvertiser(advertiser));
    }

    @GetMapping("/{email}")                //Sowndarya
    public ResponseEntity<?> getAdvertiserByEmail(@PathVariable String email) {
        Optional<Advertiser> advertiser = advertiserService.findByEmail(email);
        if (advertiser.isPresent()) {
            return ResponseEntity.ok(advertiser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{email}")       //Vaibhav
    public ResponseEntity<?> updateAdvertiserByEmail(@PathVariable String email, @Valid @RequestBody Advertiser advertiser) {
        Optional<Advertiser> updatedAdvertiserOptional = advertiserService.updateAdvertiserByEmail(email, advertiser);
        
        if (updatedAdvertiserOptional.isPresent()) {
            return ResponseEntity.ok(updatedAdvertiserOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{email}")     //Sowndarya
    public ResponseEntity<Void> deleteAdvertiserByEmail(@PathVariable String email) {
        boolean deleted = advertiserService.deleteAdvertiserByEmail(email);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
