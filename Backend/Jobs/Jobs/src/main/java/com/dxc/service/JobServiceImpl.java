package com.dxc.service;

import com.dxc.model.Job;
import com.dxc.model.User;
import com.dxc.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobsRepository jobsRepository;

    @Override
    public void saveJob(Job job) {
        jobsRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobsRepository.findAll();
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobsRepository.findById(id);
    }

    @Override
    public List<User> getJobApplicants(Long jobId) {
        Optional<Job> optionalJob = jobsRepository.findById(jobId);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            return new ArrayList<>(job.getUsersApplied());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Job> getRecommendedJobs(Long userId) {

        List<Job> recommendedJobs = new ArrayList<>();

        List<Job> jobsByLocation = jobsRepository.findJobsByLocation("user_location");
        List<Job> jobsBySkills = jobsRepository.findJobsBySkills("user_skills");
        Set<Job> combinedJobs = new HashSet<>(jobsByLocation);
        combinedJobs.addAll(jobsBySkills);
        Set<Long> appliedJobIds = Set.of(1L, 2L, 3L);
        combinedJobs.removeIf(job -> appliedJobIds.contains(job.getJobid()));
        recommendedJobs.addAll(combinedJobs);

        return recommendedJobs;
    }

}
