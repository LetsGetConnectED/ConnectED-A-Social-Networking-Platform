//package com.dxc.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.dxc.dto.JwtAuthenticationResponse;
//import com.dxc.dto.RefreshTokenRequest;
//import com.dxc.dto.SignUpRequest;
//import com.dxc.dto.SigninRequest;
//import com.dxc.model.Role;
//import com.dxc.model.User;
//import com.dxc.repository.UserRepository;
//
//import java.util.HashMap;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService {
//    
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JWTService jwtService;
//    
//
//    
//    public User signup(SignUpRequest signUpRequest) {
//        User user = new User();
//        user.setUseremail(signUpRequest.getUseremail());
//        user.setUsername(signUpRequest.getUsername());
//        user.setRole(Role.USER);
//        user.setUserpassword(passwordEncoder.encode(signUpRequest.getUserpassword()));
//        
//        return userRepository.save(user);    
//
//        
//    }
//    
//    @Override
//    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUseremail(), signinRequest.getUserpassword()));
//
//        var user = userRepository.findByUseremail(signinRequest.getUseremail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
//        var jwt = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
//
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//        jwtAuthenticationResponse.setToken(jwt);
//        jwtAuthenticationResponse.setRefreshToken(refreshToken);
//        return jwtAuthenticationResponse;
//    }
//    
//    @Override
//    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        String useremail = jwtService.extractUserName(refreshTokenRequest.getToken());
//        User user = userRepository.findByUseremail(useremail).orElseThrow();
//        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
//            var jwt = jwtService.generateToken(user);
//
//            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//            jwtAuthenticationResponse.setToken(jwt);
//            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
//            return jwtAuthenticationResponse;
//        }
//        return null;
//    }
//
//}

package com.dxc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.config.JwtTokenProvider;
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

    @Autowired
    private JWTService jwtService;
    @Autowired
    private JwtTokenProvider tokenprovider;
    
    @Override
    public User signup(SignUpRequest signUpRequest) {
        String password = signUpRequest.getUserpassword();
//        if (password == null || password.isEmpty()) {
//            throw new IllegalArgumentException("Password cannot be null or empty");
//        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signUpRequest.getUseremail(),
                        signUpRequest.getUserpassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenprovider.generateToken(authentication);

        // Set the token in the User object or return it separately
        
        User user = new User();
        user.setUseremail(signUpRequest.getUseremail());
        user.setUsername(signUpRequest.getUsername());
        user.setRole(Role.USER);
        user.setUserpassword(passwordEncoder.encode(password));
        user.setToken(jwt);
        
        return userRepository.save(user);
   
     
     
    }

  
    
    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUseremail(), signinRequest.getUserpassword()));

        var user = userRepository.findByUseremail(signinRequest.getUseremail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
    
    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwtService.extractUserName(refreshTokenRequest.getToken()); 
        User user = userRepository.findByUseremail(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            String jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}

