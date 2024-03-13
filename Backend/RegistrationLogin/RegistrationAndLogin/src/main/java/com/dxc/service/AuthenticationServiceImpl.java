package com.dxc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.dto.JwtAuthenticationResponse;
import com.dxc.dto.RefreshTokenRequest;
import com.dxc.dto.SignUpRequest;
import com.dxc.dto.SigninRequest;
import com.dxc.model.Role;
import com.dxc.model.User;
import com.dxc.repository.UserRepository;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUseremail(signUpRequest.getUseremail());
        user.setUsername(signUpRequest.getUsername());
        user.setRole(Role.USER);
        user.setUserpassword(passwordEncoder.encode(signUpRequest.getUserpassword()));
        
        return userRepository.save(user);    

        
    }
    
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUseremail(), signinRequest.getUserpassword()));
        
        var user = userRepository.findByUseremail(signinRequest.getUseremail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = JWTService.generateToken(user);
        var refreshToken = JWTService.generateRefreshToken(new HashMap<>(), user);
        
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
        
    }
    
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String useremail = JWTService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByUseremail(useremail).orElseThrow();
        if(JWTService.isTokenValid(refreshTokenRequest.getToken(),user)) {
            var jwt = JWTService.generateToken(user);
            
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
