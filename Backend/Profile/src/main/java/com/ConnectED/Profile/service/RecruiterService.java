package com.ConnectED.Profile.service;

import com.ConnectED.Profile.model.Recruiter;

public interface RecruiterService {
	Recruiter save(Recruiter recruiter);
	Recruiter getByEmail(String email);
	void deleteByEmail(String email);
	

}
