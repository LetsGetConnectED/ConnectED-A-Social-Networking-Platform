/*package com.dxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxc.dto.JwtAuthenticationResponse;
import com.dxc.dto.RefreshTokenRequest;
import com.dxc.dto.SignUpRequest;
import com.dxc.dto.SigninRequest;
import com.dxc.model.User;
import com.dxc.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        try {
            User newUser = authenticationService.signup(signUpRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

//    @PostMapping("/signin")
//    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
//        try {
//            JwtAuthenticationResponse response = authenticationService.signin(signinRequest);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
/*
 * @PostMapping("/signin") public ResponseEntity<?> signin(@RequestBody
 * SigninRequest signinRequest) { try { JwtAuthenticationResponse response =
 * authenticationService.signin(signinRequest); return
 * ResponseEntity.ok(response); } catch (IllegalArgumentException e) { return
 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).
 * body("Invalid email or password"); } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); }
 * }
 * 
 * @PostMapping("/refresh")
 * 
 * public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest
 * refreshTokenRequest) { try { JwtAuthenticationResponse response =
 * authenticationService.refreshToken(refreshTokenRequest); return
 * ResponseEntity.ok(response); } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token"); } } }
 * 
 */








package com.dxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.dto.JwtAuthenticationResponse;
import com.dxc.dto.RefreshTokenRequest;
import com.dxc.dto.SignUpRequest;
import com.dxc.dto.SigninRequest;
import com.dxc.model.User;
import com.dxc.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest signUpRequest) {
    	JwtAuthenticationResponse response = authenticationService.signup(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest) {
        JwtAuthenticationResponse response = authenticationService.signin(signinRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtAuthenticationResponse response = authenticationService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(response);
    }
}
