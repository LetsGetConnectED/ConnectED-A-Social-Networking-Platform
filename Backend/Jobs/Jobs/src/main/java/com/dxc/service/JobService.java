package com.dxc.service;

import com.dxc.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    void saveJob(Job job);

    List<Job> getAllJobs();

    Optional<Job> getJobById(Long id);

	boolean hasUserAppliedForJob(String useremail, Long jobid);
}
