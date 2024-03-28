package com.dxc.controller;

import com.dxc.dto.ErrorResponse;
import com.dxc.dto.JobDTO;
<<<<<<< HEAD

import com.dxc.exception.ObjectExistsException;
=======
>>>>>>> 77816930c4e51066f841f596e73b37895c1d5433
import com.dxc.exception.UserNotFoundException;
import com.dxc.model.Job;
import com.dxc.model.User;
import com.dxc.service.JobService;
import com.dxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.HashSet;


=======
>>>>>>> 77816930c4e51066f841f596e73b37895c1d5433
=======
import java.time.LocalDateTime;
>>>>>>> 48b0fbe02e87a595ab4c60fed6b71c3f43b9b453
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:4200")
public class JobsController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createJob(@Valid @RequestBody JobDTO jobDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Validation Error",
                    LocalDateTime.now()
            ));
        }

        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setSkills(jobDTO.getSkills());
        job.setLocation(jobDTO.getLocation()); 

        jobService.saveJob(job);

        return ResponseEntity.ok("Job created successfully");
    }

<<<<<<< HEAD
    @GetMapping("/in/{id}/jobs")
    public ResponseEntity<Set<Job>> getJobs(@PathVariable Long id) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

      Set<Job> jobs = new HashSet<>();
        return ResponseEntity.ok(jobs);
=======
    @GetMapping("/list")
    public ResponseEntity<?> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        List<JobDTO> jobDTOs = jobs.stream()
                .map(job -> new JobDTO(job.getJobid(), job.getTitle(), job.getDescription(), job.getSkills(), job.getLocation()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(jobDTOs);
>>>>>>> 77816930c4e51066f841f596e73b37895c1d5433
    }

    @PostMapping("/apply/{userid}")
    public ResponseEntity<?> applyForJob(@RequestBody JobDTO jobDTO, @PathVariable Long userid) {
        Optional<User> optionalUser = userService.getUserById(userid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Job job = new Job();
            job.setTitle(jobDTO.getTitle());
            job.setDescription(jobDTO.getDescription());
            job.setLocation(jobDTO.getLocation());
            job.setSkills(jobDTO.getSkills());
            job.setJobid(jobDTO.getjobid()); 
            job.setUserMadeBy(user);
            jobService.saveJob(job);
            
            return ResponseEntity.ok("Applied for job successfully");
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @GetMapping("/{jobId}/applicants")
    public ResponseEntity<?> getJobApplicants(@PathVariable Long jobId) {
        List<User> applicants = jobService.getJobApplicants(jobId);
        return ResponseEntity.ok(applicants);
    }

    @GetMapping("/in/{id}/recommended-jobs")
    public ResponseEntity<?> getRecommendedJobs(@PathVariable Long id) {
        List<Job> recommendedJobs = jobService.getRecommendedJobs(id);
        List<JobDTO> recommendedJobDTOs = recommendedJobs.stream()
                .map(job -> new JobDTO(job.getJobid(), job.getTitle(), job.getDescription(), job.getSkills(), job.getLocation()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(recommendedJobDTOs);
    }
}
