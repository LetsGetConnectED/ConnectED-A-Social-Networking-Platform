package com.connected.profile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.connected.profile.exception.ResourceNotFoundException;
import com.connected.profile.model.Recruiter;
import com.connected.profile.repository.RecruiterRepository;

@Service
public class RecruiterServiceImpl implements RecruiterService{
	@Autowired
    private RecruiterRepository recruiterRepository;
	
	@Override
	public Recruiter save(Recruiter recruiter) {
	    return recruiterRepository.save(recruiter);
	}

	@Override
	public Recruiter getByEmail(String email) {
	    Optional<Recruiter> recruiterOptional = Optional.ofNullable(recruiterRepository.findByEmail(email));
	    return recruiterOptional.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public void deleteByEmail(String email) {
	    Recruiter recruiter = recruiterRepository.findByEmail(email);
	    if (recruiter != null) {
	        recruiterRepository.delete(recruiter);
	    } else {
	        throw new IllegalArgumentException("Email cannot be null");
	    }
	}
	
}
