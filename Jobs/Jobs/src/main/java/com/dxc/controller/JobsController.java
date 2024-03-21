package com.dxc.controller;

import com.dxc.dto.JobDTO;
import com.dxc.exception.ObjectExistsException;
import com.dxc.exception.UserNotFoundException;
import com.dxc.model.Job;
import com.dxc.model.User;
import com.dxc.repository.JobsRepository;
import com.dxc.repository.UserRepository;
import com.dxc.service.JobService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jobs")
public class JobsController {

    private  JobsRepository jobRepository;
    private UserRepository userRepository;

    @PostMapping("/in/{id}/new-job")
    public ResponseEntity<String> newJob(@PathVariable Long id, @Valid @RequestBody Job job) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        job.setUserMadeBy(currentUser);
        jobRepository.save(job);

        return ResponseEntity.ok("Job created successfully");
    }

    @GetMapping("/in/{id}/jobs")
    public ResponseEntity<Set<Job>> getJobs(@PathVariable Long id) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        Set<Job> jobs = new HashSet<>(currentUser.getJobsCreated());
        return ResponseEntity.ok(jobs);
    }

    @PutMapping("/in/{id}/jobs/make-application/{jobId}")
    public ResponseEntity<String> newApplication(@PathVariable Long id, @PathVariable Long jobId) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found with id " + jobId));

        Set<User> usersApplied = job.getUsersApplied();
        if (!usersApplied.contains(currentUser)) {
            usersApplied.add(currentUser);
            job.setUsersApplied(usersApplied);
            jobRepository.save(job);
            return ResponseEntity.ok("Application submitted successfully");
        } else {
            return ResponseEntity.badRequest().body("Application already made for this job");
        }
    }

    @GetMapping("/in/{id}/jobs/{jobId}/applicants")
    public ResponseEntity<Set<User>> getJobApplicants(@PathVariable Long id, @PathVariable Long jobId) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found with id " + jobId));

        Set<User> applicants = job.getUsersApplied();
        return ResponseEntity.ok(applicants);
    }


    @GetMapping("/in/{id}/recommended-jobs")
    public ResponseEntity<List<Job>> getRecommendedJobs(@PathVariable Long id) {
        List<Job> recommendedJobs = JobService.getRecommendedJobs(id);

        return ResponseEntity.ok(recommendedJobs);
    }
}