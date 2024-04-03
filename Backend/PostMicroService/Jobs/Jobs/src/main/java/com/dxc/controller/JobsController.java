package com.dxc.controller;

import com.dxc.dto.ErrorResponse;

import com.dxc.dto.JobDTO;
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
                .map(job -> new JobDTO(job.getJobid(), job.getTitle(), job.getDescription(), job.getSkills(), job.getLocation()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(jobDTOs);
    }

    @PostMapping("/apply/{useremail}")
    public ResponseEntity<?> applyForJob(@RequestBody JobDTO jobDTO, @PathVariable String useremail) {
        Optional<User> optionalUser = userService.getUserByEmail(useremail);
        if (optionalUser.isPresent() ) {
            User user = optionalUser.get();
            Job job = new Job();
            job.setTitle(jobDTO.getTitle());
            job.setDescription(jobDTO.getDescription());
            job.setLocation(jobDTO.getLocation());
            job.setSkills(jobDTO.getSkills());
            job.setJobid(jobDTO.getjobid()); 
            job.setUserMadeBy(user);
//            jobService.saveJob(job);
        
            boolean hasApplied = jobService.hasUserAppliedForJob(user.getUseremail(), job.getJobid());
            
            if (hasApplied) {
                return ResponseEntity.badRequest().body("You have already applied for this job.");
            }
 
           jobService.saveJob(job);
           
            
            return ResponseEntity.ok("Applied for job successfully");
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

//    @GetMapping("applicants/{jobid}")
//    public ResponseEntity<?> getJobApplicants(@PathVariable Long jobid) {
//        List<User> applicants = jobService.getJobApplicants(jobid);
//        return ResponseEntity.ok(applicants);
//    }

//    @GetMapping("/in/{userid}/recommended-jobs")
//    public ResponseEntity<?> getRecommendedJobs(@PathVariable Long userid) {
//        List<Job> recommendedJobs = jobService.getRecommendedJobs(userid);
//        List<JobDTO> recommendedJobDTOs = recommendedJobs.stream()
//                .map(job -> new JobDTO(job.getJobid(), job.getTitle(), job.getDescription(), job.getSkills(), job.getLocation()))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(recommendedJobDTOs);
//    }
}