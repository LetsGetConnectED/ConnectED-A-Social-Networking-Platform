package com.dxc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.management.relation.Role;

import io.micrometer.common.lang.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;
    @Column(nullable = false)
    @NonNull
    private String username;
    @Column(nullable = false)
    @NonNull
    private String useremail;
    @Column(nullable = false)
    @NonNull
    private String userpassword;


    @OneToMany(mappedBy = "userMadeBy", fetch = FetchType.LAZY)
    private Set<Job> jobsCreated = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "job_application",
//        joinColumns = @JoinColumn(name = "userid"),
//        inverseJoinColumns = @JoinColumn(name = "jobid")
//    )
//    private Set<Job> jobApplications = new HashSet<>();

    @ManyToMany(mappedBy = "applicants")
    private Set<Job> appliedJobs = new HashSet<>();
    
    
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}


	
//	public Set<Job> getJobApplications() {
//		return jobApplications;
//	}
//
//	public void setJobApplications(Set<Job> jobApplications) {
//		this.jobApplications = jobApplications;
//	}

	public Set<Job> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(Set<Job> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
  
}




    
   