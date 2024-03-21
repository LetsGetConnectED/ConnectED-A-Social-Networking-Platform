package com.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.model.Job;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {

	boolean existsByTitle(String title);
	
}