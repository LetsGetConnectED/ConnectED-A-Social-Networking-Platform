package com.dxc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.micrometer.common.lang.NonNull;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobid;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false, length = 1500)
    @Size(max = 1500)
    private String description;
    
    private String skills;
    private String location;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp timestamp;
 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = "jobsCreated", allowSetters = true)
    private User userMadeBy;

    @ManyToMany
    @JoinTable(
        name = "job_user",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> usersApplied;

    @ManyToMany(mappedBy = "recommendedJobs", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "recommendedJobs", allowSetters = true)
    private Set<User> recommendedTo = new HashSet<>();

    
    

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

	public User getUserMadeBy() {
		return userMadeBy;
	}

	public void setUserMadeBy(User userMadeBy) {
		this.userMadeBy = userMadeBy;
	}

	public Set<User> getRecommendedTo() {
		return recommendedTo;
	}

	public void setRecommendedTo(Set<User> recommendedTo) {
		this.recommendedTo = recommendedTo;
	}

	public Set<User> getUsersApplied() {
		return usersApplied;
	}

	public void setUsersApplied(Set<User> usersApplied) {
		this.usersApplied = usersApplied;
	}

   
}
