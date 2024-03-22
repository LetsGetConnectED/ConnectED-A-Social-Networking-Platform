
package com.dxc.model;


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

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userid, String username, String useremail, String userpassword, Role role,String userMadeBy) {
		super();
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.userpassword = userpassword;
		this.role = role;
		this.userMadeBy = userMadeBy;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

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
	}

	public Object getJobsCreated() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
   


}
