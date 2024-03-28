package com.dxc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.model.Search;
import com.dxc.repository.SearchRepository;

@Service
public class SearchServiceImplementation implements SeachService {
	
	@Autowired 
	private SearchRepository   sr;

	 public SearchServiceImplementation( SearchRepository sr) {
	        this.sr = sr;
	    }

	    @Override
	    public List<Search> searchusername(String username) {
	        List<Search> search = sr.searchusername(username);
	        return search;
	    }

	   

	    @Override
	    public Search create(Search search) {
	        return sr.save(search);
	    }
}
