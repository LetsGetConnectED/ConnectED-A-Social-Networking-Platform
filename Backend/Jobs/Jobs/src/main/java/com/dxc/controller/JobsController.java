package com.dxc.controller;

import com.dxc.dto.ErrorResponse;

import com.dxc.dto.JobDTO;
import com.dxc.exception.JobNotFoundException;
import com.dxc.exception.UserNotFoundException;
import com.dxc.model.Job;
import com.dxc.model.RequestStatus;
import com.dxc.model.User;
import com.dxc.service.JobService;
import com.dxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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


    @GetMapping("/list")
    public ResponseEntity<?> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        List<JobDTO> jobDTOs = jobs.stream()
                .map(job -> {
                    RequestStatus status = determineStatusForJob(job);
                    List<String> applicants = job.getApplicants().stream()
                            .map(User::getUseremail)
                            .collect(Collectors.toList());
                    return new JobDTO(job.getJobid(), job.getTitle(), job.getDescription(), job.getSkills(), job.getLocation(), status, applicants);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(jobDTOs);
    }

    private RequestStatus determineStatusForJob(Job job) {
        boolean hasApplicants = !job.getApplicants().isEmpty();
        return hasApplicants ? RequestStatus.APPLIED : RequestStatus.APPLY;
    }

//
//    @PostMapping("/apply/{jobid}/{useremail}")
//    public ResponseEntity<?> applyForJob(@RequestBody JobDTO jobDTO, @PathVariable String useremail, @PathVariable Long jobid) {
//        Optional<User> optionalUser = userService.getUserByEmail(useremail, jobid);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            Optional<Job> optionalJob = jobService.getJobById(jobid);
//            if (optionalJob.isPresent()) {
//                Job job = optionalJob.get();
//
//                // Check if the user has already applied for this job
//                boolean hasApplied = jobService.hasUserAppliedForJob(user.getUseremail(), jobid);
//
//                if (hasApplied) {
//                    return ResponseEntity.badRequest().body("You have already applied for this job.");
//                } else {
//                    // Set the status based on whether the user has applied or not
//                    RequestStatus status = RequestStatus.APPLIED;
//
//                    // Add the user to the list of applicants
//                    job.getApplicants().add(user);
//
//                    // Update job details
//                    job.setTitle(jobDTO.getTitle());
//                    job.setDescription(jobDTO.getDescription());
//                    job.setLocation(jobDTO.getLocation());
//                    job.setSkills(jobDTO.getSkills());
//
//                    // Set the status for this particular user
//                    job.setStatus(status);
//
//                    // Set the user who made the job application
//                    job.setUserMadeBy(user);
//
//                    // Save the updated job entity
//                    jobService.saveJob(job);
//
//                    return ResponseEntity.ok("Applied for job successfully");
//                }
//            } else {
//                return ResponseEntity.badRequest().body("Job not found");
//            }
//        } else {
//            return ResponseEntity.badRequest().body("User not found");
//        }
//    }
//

    @PostMapping("/apply/{jobid}/{useremail}")
    public ResponseEntity<?> applyForJob(@RequestBody JobDTO jobDTO, @PathVariable String useremail, @PathVariable Long jobid) {
        // Retrieve user by email
        Optional<User> optionalUser = userService.getUserByEmail(useremail, jobid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Job> optionalJob = jobService.getJobById(jobid);
            if (optionalJob.isPresent()) {
                Job job = optionalJob.get();
                boolean hasApplied = job.getApplicants().stream().anyMatch(applicant -> applicant.getUseremail().equals(useremail));
                if (hasApplied) {
                    return ResponseEntity.badRequest().body("You have already applied for this job.");
                } else {
                    // Set the status based on whether the user has applied or not
                    RequestStatus status = RequestStatus.APPLIED;

                    // Add the user to the list of applicants
                    job.getApplicants().add(user);

                    // Update job details
                    job.setTitle(jobDTO.getTitle());
                    job.setDescription(jobDTO.getDescription());
                    job.setLocation(jobDTO.getLocation());
                    job.setSkills(jobDTO.getSkills());

                    // Set the status for this particular user
                    job.setStatus(status);

                    // Set the user who made the job application
                    job.setUserMadeBy(user);

                    // Save the updated job entity
                    jobService.saveJob(job);

                    return ResponseEntity.ok("Applied for job successfully");
                }
            } else {
                return ResponseEntity.badRequest().body("Job not found");
            }
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }


}