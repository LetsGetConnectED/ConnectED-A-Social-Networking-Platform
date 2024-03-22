package com.dxc.service;

import java.util.List;
import com.dxc.model.Job;
import com.dxc.model.User;
import java.util.List;
import java.util.Optional;

public interface JobService {
    void saveJob(Job job);
    List<Job> getAllJobs();
    Optional<Job> getJobById(Long id);
    List<User> getJobApplicants(Long jobId);
    List<Job> getRecommendedJobs(Long userId);
 
}
