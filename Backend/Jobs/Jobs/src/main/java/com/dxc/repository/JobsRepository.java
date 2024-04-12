package com.dxc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.model.Job;
import com.dxc.model.RequestStatus;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {

	boolean existsByTitle(String title);

	List<Job> findJobsByLocation(String string);

	List<Job> findJobsBySkills(String string);

	boolean existsByUserMadeByUseremailAndJobid(String useremail, Long jobid);
	
	  List<Job> findByStatus(RequestStatus status);
	  
	   @Query("SELECT DISTINCT j FROM Job j LEFT JOIN FETCH j.applicants WHERE j.jobid = :jobId")
	    Optional<Job> findByIdWithApplicants(@Param("jobId") Long jobId);
	
}