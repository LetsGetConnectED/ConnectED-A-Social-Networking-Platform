package com.dxc.dto;

<<<<<<< HEAD
import org.springframework.stereotype.Component;

import com.dxc.model.Role;

@Component
=======
import lombok.Getter;
import lombok.Setter;
>>>>>>> 9083e49908be6b2278f251a13d4aec9b7b969535

@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String useremail;
    private String userpassword;
    private String role;
<<<<<<< HEAD
   
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
=======
	public String getUsername() {
		return username;
	}
>>>>>>> 9083e49908be6b2278f251a13d4aec9b7b969535
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 9083e49908be6b2278f251a13d4aec9b7b969535
	
    

	}


