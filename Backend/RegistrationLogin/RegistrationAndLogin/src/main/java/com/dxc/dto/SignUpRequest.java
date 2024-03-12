package com.dxc.dto;

import lombok.Data;

@Data

public class SignUpRequest {
	
	private String username;
	private String useremail;
	private String userpassword;
	
	 public String getUseremail() {
	        return useremail;
	    }

	    public void setUseremail(String useremail) {
	        this.useremail = useremail;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getUserpassword() {
	        return userpassword;
	    }

	    public void setUserpassword(String password) {
	        this.userpassword = userpassword;
	    }
	}


