package com.dxc.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.model.Candidate;
import com.dxc.repo.CandioateDAO;
import com.dxc.service.CandidateService;
import com.dxc.service.CandidateServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceTest {
	
	
	@MockBean
	CandioateDAO repo;
	
	@Autowired
	CandidateServiceImpl candidateService;
	
	@Test
	public void getAllCandidatesTest() {
		List<Candidate> candidates = Arrays.asList(new Candidate(1, 123 , "John", 99, 1, new java.sql.Date(new java.util.Date(System.currentTimeMillis()).getDate())), new Candidate(2, 1223 , "Joe", 9, 13, new java.sql.Date(new java.util.Date(System.currentTimeMillis()).getDate())));
		Mockito.when(repo.findAll()).thenReturn(candidates);
		assertEquals(2, candidateService.getAllCandidates().size());
		System.out.println("Successful in getting candidate list");
	}
 
}
 
