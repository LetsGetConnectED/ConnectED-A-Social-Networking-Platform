package com.ConnectED.Profile.service;

import java.util.List;

import com.ConnectED.Profile.model.Profile;


public interface ProfileService {
	

		Profile saveOrUpdate(Profile profile);
		Profile getByEmail(String email);
		void deleteByEmail(String email);

}
