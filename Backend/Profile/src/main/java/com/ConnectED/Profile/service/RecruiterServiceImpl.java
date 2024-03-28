package com.ConnectED.Profile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ConnectED.Profile.exception.DeletedException;
import com.ConnectED.Profile.exception.ResourceNotFoundException;
import com.ConnectED.Profile.model.Recruiter;
import com.ConnectED.Profile.repository.RecruiterRepository;

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
	public void deleteByEmail(String email) throws DeletedException {
	    Recruiter recruiter = recruiterRepository.findByEmail(email);
	    if (recruiter != null) {
	        recruiterRepository.delete(recruiter);
	        throw new DeletedException();
	    } else {
	        throw new IllegalArgumentException("Email cannot be null");
	    }
	}
	
}
