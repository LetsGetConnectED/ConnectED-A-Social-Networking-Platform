package com.dxc.model;

<<<<<<< HEAD

//import jakarta.persistence.Entity;
//
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//
//import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

import javax.management.relation.Role;

//@Entity
public class User {
//	@Id
    private int userid; 
    
    private String username;
    private String useremail;
    private String userpassword;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private String userMadeBy;
    
//    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "recommendedTo")
    private List<Job> recommendedJobs;
=======
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
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    private String username;
    private String useremail;
    private String userpassword;
>>>>>>> 77816930c4e51066f841f596e73b37895c1d5433


<<<<<<< HEAD
	public User(int userid, String username, String useremail, String userpassword, Role role,String userMadeBy) {
		super();
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.userpassword = userpassword;
		this.role = role;
		this.userMadeBy = userMadeBy;
	}
=======
    @OneToMany(mappedBy = "userMadeBy", fetch = FetchType.LAZY)
    private Set<Job> jobsCreated = new HashSet<>();
>>>>>>> 77816930c4e51066f841f596e73b37895c1d5433

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "job_application",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<Job> jobApplications = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recommended_jobs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<Job> recommendedJobs = new HashSet<>();

    @ManyToMany(mappedBy = "usersApplied")
    private Set<Job> appliedJobs;

	

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


	public Set<Job> getRecommendedJobs() {
		return recommendedJobs;
	}

	public void setRecommendedJobs(Set<Job> recommendedJobs) {
		this.recommendedJobs = recommendedJobs;
	}

<<<<<<< HEAD
	public String getUserMadeBy() {
		return userMadeBy;
	}

	public void setUserMadeBy(String userMadeBy) {
		this.userMadeBy = userMadeBy;
	}

	public List<Job> getRecommendedJobs() {
		return recommendedJobs;
	}

	public void setRecommendedJobs(List<Job> recommendedJobs) {
		this.recommendedJobs = recommendedJobs;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", useremail=" + useremail + ", userpassword="
				+ userpassword + ", role=" + role + "]";
=======
	public void setUsername(String username) {
		this.username = username;
>>>>>>> 77816930c4e51066f841f596e73b37895c1d5433
	}

    
	public Object getJobsCreated() {
		// TODO Auto-generated method stub
		return null;
	}
  
}




    
   