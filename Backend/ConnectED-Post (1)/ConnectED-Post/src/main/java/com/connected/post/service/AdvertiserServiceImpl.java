package com.connected.post.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connected.post.model.Advertiser;
import com.connected.post.repository.AdvertiserRepository;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {

    @Autowired
    private AdvertiserRepository advertiserRepository;

    @Override
    public Advertiser createAdvertiser(Advertiser advertiser) {
        return advertiserRepository.save(advertiser);
    }

    public Optional<Advertiser> findByEmail(String email) {
        return advertiserRepository.findByEmail(email);
    }

    @Override
    public Optional<Advertiser> updateAdvertiserByEmail(String email, Advertiser advertiserDetails) {
        return advertiserRepository.findByEmail(email).map(advertiser -> {
            if (advertiserDetails.getEmail() != null) {
                advertiser.setEmail(advertiserDetails.getEmail());
            }
            if (advertiserDetails.getFirstName() != null) {
                advertiser.setFirstName(advertiserDetails.getFirstName());
            }
            if (advertiserDetails.getLastName() != null) {
                advertiser.setLastName(advertiserDetails.getLastName());
            }
           

            return advertiserRepository.save(advertiser);
        });
    }


    @Override
    public boolean deleteAdvertiserByEmail(String email) {
        return advertiserRepository.findByEmail(email).map(advertiser -> {
            advertiserRepository.delete(advertiser);
            return true;
        }).orElse(false);
    }

	@Override
	public Advertiser save(Advertiser advertiser) {
		return advertiserRepository.save(advertiser);
	}
}
