package com.dxc.service;

import com.dxc.model.Job;
import com.dxc.model.User;
import com.dxc.repository.JobsRepository;
import com.dxc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobsRepository jobsRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String useremail,Long jobid) {
        return userRepository.findByUseremail(useremail);
       
        	
        
       
    }
    
    

//    @Override
//    public List<User> getJobApplicants(Long jobid) {
//        Optional<Job> optionalJob = jobsRepository.findById(jobid);
//        if (optionalJob.isPresent()) {
//         
//            Job job = optionalJob.get();
//            
//            System.out.println("got it");
//            return new ArrayList<>(job.getUsersApplied());
//        } else {
//            return new ArrayList<>();
//        }
//    }
}
