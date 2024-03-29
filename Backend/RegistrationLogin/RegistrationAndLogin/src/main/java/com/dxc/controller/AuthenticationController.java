package com.dxc.controller;

import com.dxc.dto.JwtAuthenticationResponse;
import com.dxc.dto.SignUpRequest;
import com.dxc.dto.SigninRequest;
import com.dxc.exception.UserNotFoundException;
import com.dxc.model.User;
import com.dxc.service.AuthenticationService;
import com.dxc.service.JWTServiceImpl;
import com.dxc.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	
    private final AuthenticationService authenticationService;

    @Autowired
    private JWTServiceImpl jwtServiceImpl;
   
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
  

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        JwtAuthenticationResponse response = authenticationService.signin(signinRequest);
        return ResponseEntity.ok(response);
    }
//    
//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@RequestHeader(name="Authorization") String token, @RequestBody String password) {
//    	String username=jwtServiceImpl.extractUserName(token.substring(7));
//    	User response = authenticationService.forgotPassword(username, password);
//        return ResponseEntity.ok(response);
//    }
//    
    

   
}
