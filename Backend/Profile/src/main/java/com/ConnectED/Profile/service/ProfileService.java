package com.connected.profile.service;

import com.connected.profile.model.Profile;

public interface ProfileService {
	

	Profile saveOrUpdate(Profile profile);
	Profile getByEmail(String email);
	void deleteByEmail(String email);

}