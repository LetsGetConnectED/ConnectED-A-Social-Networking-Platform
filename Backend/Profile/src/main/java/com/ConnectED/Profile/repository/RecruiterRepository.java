package com.ConnectED.Profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ConnectED.Profile.model.Recruiter;
@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

	Recruiter findByEmail(String email);

}
