package com.dxc.dto;

import com.dxc.model.Role;



public class SignUpRequest {
	private Long userid;
    private String username;
    private String useremail;
    private String userpassword;
    private String role;
<<<<<<< HEAD
=======
    private String token;
	public long getUserid() {
		return userid;
	}
>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e
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
	public String getToken() {
		return token;
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
<<<<<<< HEAD
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
    
=======
	public void setRole(String role) {
		this.role = role;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e

	}


