package com.dxc.service;

import org.springframework.security.core.userdetails.UserDetails;

public class JWTService {

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public String generatetoken(UserDetails userDetails) {
		return null;
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		return true;
	    
	}
}