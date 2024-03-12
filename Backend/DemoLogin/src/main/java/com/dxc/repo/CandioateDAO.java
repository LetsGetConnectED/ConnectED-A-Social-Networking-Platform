package com.dxc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.model.Candidate;

@Repository
public interface CandioateDAO extends JpaRepository<Candidate, Long>{

}
