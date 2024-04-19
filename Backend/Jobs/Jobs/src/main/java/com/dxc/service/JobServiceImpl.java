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

    // @Override
    // public void saveJob(Job job) {
    //     if (job.getStatus() == RequestStatus.APPLY) {
    //         job.setStatus(RequestStatus.APPLIED);
    //     }
    //     jobsRepository.save(job);
    // }
    public void saveJob(Job job) {
        if (job.getStatus() == RequestStatus.APPLY) {
            job.setStatus(RequestStatus.APPLIED);
        }

        // Generate recruiterId
        Long recruiterid = recruiterIdGenerationService.generateRecruiterId();
        job.setRecruiterid(recruiterid);

        // Save job
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
@Override
public List<Job> getJobsByRecruiter(Long recruiterid) {
	
	  return jobsRepository.findByRecruiterid(recruiterid);
}



@Override
@Transactional
public void deletejob(Long jobid) {
  
    if (jobsRepository.existsById(jobid)) {
     
           jobsRepository.deleteById(jobid);
        System.out.println("Job with ID " + jobid + " has been deleted.");
    } else {
      
        System.out.println("Job with ID " + jobid + " does not exist.");
    }
}

}
