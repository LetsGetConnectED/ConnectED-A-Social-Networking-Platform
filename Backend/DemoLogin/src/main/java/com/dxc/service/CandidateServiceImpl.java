package com.dxc.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.model.Candidate;

import com.dxc.repo.CandioateDAO;

@Service
	public class CandidateServiceImpl{
	 
	    @Autowired
	    private CandioateDAO cr;
	 
	    public List<Candidate> getAllCandidates() {
	        return cr.findAll();
	    }
}
