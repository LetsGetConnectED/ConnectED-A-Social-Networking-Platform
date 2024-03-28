package com.dxc.service;

import java.util.List;

import com.dxc.model.Search;

public interface SeachService {
	 List<Search> searchusername(String username);

	    Search create(Search search);
}
