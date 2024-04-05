package com.connected.profile.service;

import com.connected.profile.model.Advertiser;

public interface AdvertiserService {
	
	Advertiser save(Advertiser advertiser);
	Advertiser getByEmail(String email);
	void deleteByEmail(String email);

}
