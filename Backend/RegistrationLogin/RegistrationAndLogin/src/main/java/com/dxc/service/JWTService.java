package com.dxc.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

//    String generateRefreshToken(UserDetails userDetails);
//    
//    String generateRefreshToken(String string);

//    boolean validateRefreshToken(String refreshToken);
//
//    String extractUserEmail(String refreshToken);

//	String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
