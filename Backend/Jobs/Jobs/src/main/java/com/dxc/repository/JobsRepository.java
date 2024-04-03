package com.dxc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.model.Job;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {

	boolean existsByTitle(String title);

	List<Job> findJobsByLocation(String string);

	List<Job> findJobsBySkills(String string);

	boolean existsByUserMadeByUseremailAndJobid(String useremail, Long jobid);
	
}