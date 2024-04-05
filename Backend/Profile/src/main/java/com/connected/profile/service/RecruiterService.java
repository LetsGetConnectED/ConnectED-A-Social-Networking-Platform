package com.connected.profile.service;

import com.connected.profile.model.Recruiter;

public interface RecruiterService {
	Recruiter save(Recruiter recruiter);
	Recruiter getByEmail(String email);
	void deleteByEmail(String email);
	

}
