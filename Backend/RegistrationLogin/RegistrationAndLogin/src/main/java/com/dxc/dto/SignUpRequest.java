package com.dxc.dto;

import org.springframework.stereotype.Component;

import com.dxc.model.Role;

@Component

public class SignUpRequest {
	private Long userid;
    private String username;
    private String useremail;
    private String userpassword;
    private String role;
   
	public long getUserid() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
	public String getUseremail() {
		return useremail;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public String getRole() {
		return role;
	}
	
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

	}


