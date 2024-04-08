package com.connected.advertisement.service;

import java.util.Optional;

import com.connected.advertisement.model.Advertiser;

public interface AdvertiserService {
	Advertiser createAdvertiser(Advertiser advertiser);
	 Optional<Advertiser> findByEmail(String email);
	 Advertiser save(Advertiser advertiser);
    Optional<Advertiser> updateAdvertiserByEmail(String email, Advertiser advertiserDetails);
    boolean deleteAdvertiserByEmail(String email);

}
