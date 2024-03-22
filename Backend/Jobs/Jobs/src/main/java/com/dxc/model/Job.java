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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false, length = 1500)
    @Size(max = 1500)
    private String description;

    @Column
    private Timestamp timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"jobsCreated","jobApplied","recommendedJobs","interestReactions"}, allowSetters = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User userMadeBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"jobApplied","jobsCreated","recommendedJobs","interestReactions"}, allowSetters = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> usersApplied = new HashSet<>();

    @ManyToMany(mappedBy = "recommendedJobs", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"recommendedJobs","jobsCreated","jobApplied","interestReactions","usersFollowing","userFollowedBy","posts"}, allowSetters = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> recommendedTo = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<User> getUsersApplied() {
		return usersApplied;
	}

	public void setUsersApplied(Set<User> usersApplied) {
		this.usersApplied = usersApplied;
	}

	public List<User> getRecommendedTo() {
		return recommendedTo;
	}

	public void setRecommendedTo(List<User> recommendedTo) {
		this.recommendedTo = recommendedTo;
	}

    
}
