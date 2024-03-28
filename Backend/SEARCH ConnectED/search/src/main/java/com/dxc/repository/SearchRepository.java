package com.dxc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.model.Search;

@Repository

public interface SearchRepository extends JpaRepository<Search, Long> {
	
	
	  List<Search> searchusername(String username);

}
