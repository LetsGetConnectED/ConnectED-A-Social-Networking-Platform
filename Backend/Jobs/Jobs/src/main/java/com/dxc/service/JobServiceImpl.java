package com.dxc.service;

import com.dxc.model.Job;
import com.dxc.model.RequestStatus;
import com.dxc.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobsRepository jobsRepository;

    @Override
    public void saveJob(Job job) {
        if (job.getStatus() == RequestStatus.APPLY) {
            job.setStatus(RequestStatus.APPLIED);
        }
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

//    @Override
//    public boolean hasUserAppliedForJob(String useremail, Long jobid) {
//        return jobsRepository.existsByUserMadeByUseremailAndJobid(useremail, jobid);
//    }
    
    @Override
    public boolean hasUserAppliedForJob(String useremail, Long jobid) {
        Optional<Job> optionalJob = jobsRepository.findById(jobid);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            return job.getApplicants().stream()
                    .anyMatch(applicant -> applicant.getUseremail().equals(useremail));
        }
        return false;
}
}
