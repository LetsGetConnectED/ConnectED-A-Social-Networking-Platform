package com.ConnectED.Profile.service;

import com.ConnectED.Profile.model.Advertiser;
import com.ConnectED.Profile.model.Profile;

public interface AdvertiserService {
	
	Advertiser save(Advertiser advertiser);
	Advertiser getByEmail(String email);
	void deleteByEmail(String email);
	Advertiser updateByEmail(String email);

}
