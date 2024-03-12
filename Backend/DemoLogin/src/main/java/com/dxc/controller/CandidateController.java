package com.dxc.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.model.Candidate;
import com.dxc.service.CandidateServiceImpl;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")

	public class CandidateController {
	 
	    @Autowired
	    private CandidateServiceImpl candidateService;
	 
	    @GetMapping("/candidates")
	    public List<Candidate> getCandidates() {
	        return candidateService.getAllCandidates();
	    }
	}
