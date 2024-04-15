package com.dxc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobid;

    @Column(nullable = false)
    @NonNull
    private String title;

    private RequestStatus status;
    
    @Column(nullable = false, length = 1500)
    @Size(max = 1500)
    private String description;
    
//    @Column(nullable = false)
//    @Size(max = 1500)
    @NotBlank(message = "Location is required")
    private String location;

//    @Column(nullable = false)
    @NotBlank(message = "Skills are required")
    private String skills;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
   @JsonIgnoreProperties(value = "jobsCreated", allowSetters = true)
    private User userMadeBy;
    
//    @ManyToMany(mappedBy = "jobApplications")
//    private Set<User> applicants = new HashSet<>();
//    
//    public Job() {
//        this.applicants = new HashSet<>();
//    }
//  
    
    @ManyToMany
    @JoinTable(
        name = "job_applicants",
        joinColumns = @JoinColumn(name = "jobid"),
        inverseJoinColumns = @JoinColumn(name = "userid")
    )
    private Set<User> applicants = new HashSet<>();

    
    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getJobid() {
        return jobid;
    }

    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
    public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Set<User> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<User> applicants) {
		this.applicants = applicants;
	}

	public User getUserMadeBy() {
        return userMadeBy;
    }

    public void setUserMadeBy(User userMadeBy) {
        this.userMadeBy = userMadeBy;
    }

	@Override
	public String toString() {
		return "Job [jobid=" + jobid + ", title=" + title + ", status=" + status + ", description=" + description
				+ ", location=" + location + ", skills=" + skills + ", timestamp=" + timestamp + ", userMadeBy="
				+ userMadeBy + ", applicants=" + applicants + "]";
	}

	public Job() {
		super();
	}
	
	

//    public Set<User> getRecommendedTo() {
//        return recommendedTo;
//    }
//
//    public void setRecommendedTo(Set<User> recommendedTo) {
//        this.recommendedTo = recommendedTo;
//    }
//
//    public Set<User> getUsersApplied() {
//        return usersApplied;
//    }
//
//    public void setUsersApplied(Set<User> usersApplied) {
//        this.usersApplied = usersApplied;
//    }
    
    
}
